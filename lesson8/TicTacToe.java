package homework8;

import java.util.Scanner;

public class TicTacToe {

    private char[] players;
    private char[][] field;
    private int[] position;

    TicTacToe(int fieldSize) {
        players = new char[] {'0', 'X'};
        field = getField(fieldSize);
    }

    private char[][] getField(int fieldSize) {
        char[][]field = new char[fieldSize][fieldSize];

        for(int row = 0; row < fieldSize; row++) {
            for(int column = 0; column < fieldSize; column++) {
                field[row][column] = ' ';
            }
        }

        return field;
    }

    public char playRound(char player) {
        int[] newSymbPos;

        do {
            newSymbPos = getPosition();
        } while(isPosExist(newSymbPos));

        updateField(field, newSymbPos, player);

//        drawField(field); // Для теста

        if(hasWinner(player)) {
            return player;
        } else if(isStandoff(player)) {
            return 1;
        }

        return 0;
    }

    private int[] getPosition() { return position; }

    public void setPosition(int x, int y) {
        position = new int[] {x, y};
    }

    private boolean hasWinner(char player) {
        return checkLines(player) || checkDiagonals(player);
    }

    /**
     * Проверка элементов, рассположенных в линию.
     * Обход выполняется по принципу L + Ꞁ для поля 3x3.
     */
    private boolean checkLines(char symb) {
        for(int row = 0; row < field.length; row++) {
            boolean checkRow = true;
            boolean checkCol = true;

            for(int col = 0; col < field.length; col++) {
                checkRow = checkRow && (field[row][col] == symb);
                checkCol = checkCol && (field[col][row] == symb);
            }

            if(checkRow || checkCol) {
                return true;
            }
        }

        return false;
    }

    /**
     * Обход выполняется одновременно по обеим диагоналям
     */
    private boolean checkDiagonals(char symb) {
        boolean checkRow = true;
        boolean checkCol = true;

        for(int row = 0; row < field.length; row++) {
            checkRow = checkRow && (field[row][row] == symb);
            checkCol = checkCol && (field[row][field.length - 1 - row] == symb);
        }

        return checkRow || checkCol;
    }

    private boolean isStandoff(char player) {
        int[][] empty = emptyPos();
        boolean[] checksStandoffs = new boolean[empty.length];

        for(int i = 0; i < checksStandoffs.length; i++) {
            char[][] copyField = new char[field.length][field.length];
            copyArrElements(field, copyField);

            updateField(copyField, empty[i], player);

            checksStandoffs[i] = checkStandoff(copyField);
        }

        return all(checksStandoffs, true);
    }

    private void copyArrElements(char[][]src, char[][] dst) {
        for(int i = 0; i < src.length; i++) {
            for(int j = 0; j < src.length; j++) {
                dst[i][j] = src[i][j];
            }
        }
    }

    private boolean all(boolean[] arr, boolean value) {
        for(boolean element : arr) {
            if(element != value) {
                return false;
            }
        }

        return true;
    }

    private int[][] emptyPos() {
        int nEmpty = 0;

        for(char[] row : field) {
            for(char symb : row) {
                if(symb == ' ') {
                    nEmpty++;
                }
            }
        }

        int[][] empty = new int[nEmpty][2];

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field.length; j++) {
                if(field[i][j] == ' ') {
                    empty[empty.length - nEmpty][0] = i;
                    empty[empty.length - nEmpty][1] = j;
                    nEmpty--;
                }
            }
        }

        return empty;
    }

    private boolean checkStandoff(char[][] field) {
        boolean checkLines = true;

        for(int row = 0; row < field.length; row++) {
            boolean checkRow = false;
            boolean checkCol = false;
            boolean isFirstHorizSymb = true;
            boolean isFirstVerticSymb = true;

            char prevRowSymb = 0;
            char prevColSymb = 0;

            for(int col = 0; col < field.length; col++) {
                char currRowSymb = field[row][col];
                char currColSymb = field[col][row];

                if(currRowSymb != ' ') {
                    if(isFirstHorizSymb) {
                        prevRowSymb = currRowSymb;
                        isFirstHorizSymb = false;
                    } else if(currRowSymb != prevRowSymb) {
                        checkRow = true;
                    }
                }

                if(currColSymb != ' ') {
                    if(isFirstVerticSymb) {
                        prevColSymb = currColSymb;
                        isFirstVerticSymb = false;
                    } else if(currColSymb != prevColSymb) {
                        checkCol = true;
                    }
                }
            }

            checkLines = checkLines && checkRow && checkCol;
        }

        boolean checkDiagonals = true;
        boolean checkLeftDiagonal = false;
        boolean checkRightDiagonal = false;
        boolean isFirstLeftDiagSymb = true;
        boolean isFirstRightDiagSymb = true;

        char prevLeftDiagSymb = 0;
        char prevRightDiagSymb = 0;

        for(int row = 0; row < field.length; row++) {
            char currLeftDiagSymb = field[row][row];
            char currRightDiagSymb = field[row][field.length - 1 - row];

            if (currLeftDiagSymb != ' ') {
                if(isFirstLeftDiagSymb) {
                    prevLeftDiagSymb = currLeftDiagSymb;
                    isFirstLeftDiagSymb = false;
                } else if(currLeftDiagSymb != prevLeftDiagSymb) {
                    checkLeftDiagonal = true;
                }
            }

            if (currRightDiagSymb != ' ') {
                if(isFirstRightDiagSymb) {
                    prevRightDiagSymb = currRightDiagSymb;
                    isFirstRightDiagSymb = false;
                } else if(currRightDiagSymb != prevRightDiagSymb) {
                    checkRightDiagonal = true;
                }
            }
        }

        checkDiagonals = checkDiagonals && checkLeftDiagonal && checkRightDiagonal;

        return checkLines && checkDiagonals;
    }

    private boolean isPosExist(int[] newSymbPos) {
        boolean posExist = field[newSymbPos[0]][newSymbPos[1]] != ' ';

        if(posExist) {
            System.out.println("Не возможно изменить значение занятой клетки.");
        }

        return posExist;
    }

    private void updateField(char[][] field, int[] newSymbPos, char player) {
        field[newSymbPos[0]][newSymbPos[1]] = player;
    }
}
