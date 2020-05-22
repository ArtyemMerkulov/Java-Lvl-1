package homework4;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        char[] symbols = new char[] {'0', 'X'};

        int fieldSize = getFieldSize();
        char[][] field = getField(fieldSize);

        char[] players = initPlayers(symbols);

        play(field, players);
    }

    public static int getFieldSize() {
        String fieldSizeLine;

        Scanner fieldSizeScanner = new Scanner(System.in);

        do {
            System.out.print("Пожалуйста, введите ширину поля: ");
            fieldSizeLine = fieldSizeScanner.nextLine();
        } while(!hasFieldSize(fieldSizeLine));

        return Integer.parseInt(fieldSizeLine);
    }

    public static boolean hasFieldSize(String fieldSizeLine) {
        if(fieldSizeLine.length() == 1) {
            if (isDigit(fieldSizeLine)) {
                int fieldSize = Integer.parseInt(fieldSizeLine);

                if(fieldSize >= 2) {
                    return true;
                } else {
                    System.out.println("Ширина поля не может быть меньше, либо равной единице.\n");
                }
            } else {
                System.out.println("Вы ввели некорректную ширину поля.\n");
            }
        } else {
            System.out.println("Вы ввели некорректную ширину поля.\n");
        }

        return false;
    }

    public static char[][] getField(int fieldSize) {
        char[][]field = new char[fieldSize][fieldSize];

        for(int row = 0; row < fieldSize; row++) {
            for(int column = 0; column < fieldSize; column++) {
                field[row][column] = ' ';
            }
        }

        return field;
    }

    public static char[] initPlayers(char[] symbols) {
        char[] players = new char[symbols.length];

        System.out.println();

        for(int iPlayer = 0; iPlayer < players.length; iPlayer++) {
            String playerSymb;
            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("Игрок №" + (iPlayer + 1) + ", пожалуйста, " +
                        "введите символ, за который будете играть - " + symbols[0] +
                        " или " + symbols[1] + ": ");
                playerSymb = scanner.nextLine();
            } while (!checkPlayer(symbols, players, playerSymb));

            players[iPlayer] = playerSymb.charAt(0);
        }

        return players;
    }

    public static boolean checkPlayer(char[] symbols, char[] players,
                                      String playerSymb) {
        if(playerSymb.length() == 1) {
            if(any(symbols, playerSymb.charAt(0)) &&
                    !any(players, playerSymb.charAt(0))) {
                return true;
            }
        }

        System.out.println("Вы ввели некорректный символ.");

        return false;
    }

    public static boolean any(char[] arr, char value) {
        for(char element : arr) {
            if(element == value) {
                return true;
            }
        }

        return false;
    }

    public static void play(char[][] field, char[] players) {
        int playerId = -1;
        int currStep = 0;

        do {
            playerId = (playerId + 1) % players.length;
            currStep++;

            int[] newSymbPos;

            do {
                newSymbPos = getPosition(playerId, field.length);
            } while(isPosExist(field, newSymbPos));

            updateField(field, newSymbPos, players[playerId]);

            drawField(field);

            if(hasWinner(field, players[playerId])) {
                System.out.println("\nПобедил Игрок №" + (playerId + 1) + "!");
                break;
            } else if(isStandoff(field, players, currStep)) {
                System.out.println("\nНичья!");
                break;
            }
        } while(true);
    }

    public static boolean hasWinner(char[][] field, char player) {
        return checkLines(field, player) || checkDiagonals(field, player);
    }

    /**
     * Проверка элементов, рассположенных в линию.
     * Обход выполняется по принципу L + Ꞁ для поля 3x3.
     */
    public static boolean checkLines(char[][] field, char symb) {
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
    public static boolean checkDiagonals(char[][] field, char symb) {
        boolean checkRow = true;
        boolean checkCol = true;

        for(int row = 0; row < field.length; row++) {
            checkRow = checkRow && (field[row][row] == symb);
            checkCol = checkCol && (field[row][field.length - 1 - row] == symb);
        }

        return checkRow || checkCol;
    }

    public static boolean isStandoff(char[][] field, char[] players, int currStep) {
        if(currStep > Math.pow(field.length, 2)) {
            return true;
        }

        int[][] empty = emptyPos(field);
        boolean[] checksStandoffs = new boolean[empty.length];

        for(int i = 0; i < checksStandoffs.length; i++) {
            char[][] copyField = new char[field.length][field.length];
            copyArrElements(field, copyField);

            updateField(copyField, empty[i], players[(currStep) % players.length]);

            checksStandoffs[i] = checkStandoff(copyField);
        }

        return all(checksStandoffs, true);
    }

    public static void copyArrElements(char[][]src, char[][] dst) {
        for(int i = 0; i < src.length; i++) {
            for(int j = 0; j < src.length; j++) {
                dst[i][j] = src[i][j];
            }
        }
    }

    public static boolean all(boolean[] arr, boolean value) {
        for(boolean element : arr) {
            if(element != value) {
                return false;
            }
        }

        return true;
    }

    public static int[][] emptyPos(char[][] field) {
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

    public static boolean checkStandoff(char[][] field) {
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

    public static int[] getPosition(int playerId, int interval) {
        int[] posIdxs = new int[2];
        String scanPosition;

        Scanner posScanner = new Scanner(System.in);

        do {
            System.out.print("\nИгрок №" + (playerId + 1) + ", пожалуйста, введите " +
                    "через пробел номер строки и столбца,на пересечении\nкоторых " +
                    "расположена клетка, в которую Вы желаете записать символ: ");

            scanPosition = posScanner.nextLine();
        } while(!checkPosition(scanPosition, posIdxs, interval));

        return posIdxs;
    }

    public static boolean checkPosition(String possition, int[] posIdxs, int interval) {
        String[] positions = possition.split(" ");

        if(positions.length != posIdxs.length) {
            System.out.print("Вы ввели некорректные координаты.");
            return false;
        }

        for (String position : positions) {
            if (isDigit(position)) {
                int userPos = Integer.parseInt(position);

                if (userPos < 1 || userPos > interval) {
                    System.out.print("Вы ввели некорректные координаты.");
                    return false;
                }
            } else {
                System.out.print("Вы ввели некорректные координаты.");
                return false;
            }
        }

        for (int i = 0; i < positions.length; i++) {
            posIdxs[i] = Integer.parseInt(positions[i]) - 1;
        }

        return true;
    }

    public static boolean isDigit(String str) {
        char[] strChars = str.toCharArray();

        for(int charOrd : strChars) {
           if(charOrd < 48 || charOrd > 57) {
                return false;
           }
        }

        return true;
    }

    public static boolean isPosExist(char[][] field, int[] newSymbPos) {
        boolean posExist = field[newSymbPos[0]][newSymbPos[1]] != ' ';

        if(posExist) {
            System.out.println("Не возможно изменить значение занятой клетки.");
        }

        return posExist;
    }

    public static void updateField(char[][] field, int[] newSymbPos, char player) {
        field[newSymbPos[0]][newSymbPos[1]] = player;
    }

    private static void drawField(char[][] field) {
        for(char[] row : field) {
            System.out.print("|");

            for(char symb : row) {
                System.out.print(symb + "|");
            }

            System.out.println();
        }
    }
}