package homework4;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        char[] symbols = new char[] {'0', 'X'};

        int fieldSize = getFieldSize();
        char[][] field = getField(fieldSize);
        //char[][]field = new char[][] {{'0', 'X', ' '}, {' ', '0', ' '}, {'X', '0', 'X'}};

        char[] players = initPlayers(symbols);

        play(field, players);
    }

    public static int getFieldSize() {
        int fieldSize;
        Scanner fieldSizeScanner = new Scanner(System.in);

        do {
            System.out.print("Пожалуйста, введите ширину поля: ");

            if(fieldSize <= 1) {
                System.out.println("Вы ввели некорректную ширину поля. " +
                        "Пожалуйста, \nвведите число большее, чем 1.");
            }
        } while(fieldSize <= 1);
        return fieldSize;
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

            if(isStandoff(field, players, currStep)) {
                System.out.println("\nНичья!");
                break;
            } else if(hasWinner(field, players[playerId])) {
                System.out.println("\nПобедил Игрок №" + (playerId + 1) + "!");
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

        if(checkRow || checkCol) {
            return true;
        }

        return false;
    }

    public static boolean isStandoff(char[][] field, char[] players, int currStep) {
        if(currStep > Math.pow(field.length, 2)) {
            return true;
        }

        boolean checkRows = true;
        boolean checkCols = true;
        // Проверка на ничейный результат в строках и колонках
        for(int row = 0; row < field.length; row++) {
            boolean checkRow = false;
            boolean checkCol = false;
            char rowVariant = 0;
            char colVariant = 0;

            for(int col = 0; col < field.length; col++) {
                if(rowVariant == 0 && any(players, field[row][col])) {
                    rowVariant = field[row][col];
                } else if(rowVariant != 0 && rowVariant != field[row][col] &&
                        any(players, field[row][col])) {
                    checkRow = true;
                }

                if(colVariant == 0 && any(players, field[col][row])) {
                    colVariant = field[col][row];
                } else if(colVariant != 0 && colVariant != field[col][row] &&
                        any(players, field[col][row])) {
                    checkCol = true;
                }

                checkRows = checkRows && checkRow;
                checkCols = checkCols && checkCol;
            }
        }
        // Проверка на ничейный результат в диагоналях
        boolean checkDiagonals = true;
        char rowVariant = 0;
        char colVariant = 0;

        for(int row = 0; row < field.length; row++) {
            boolean checkLeftDiagonal = false;
            boolean checkRightDiagonal = false;

            if(rowVariant == 0 && any(players, field[row][row])) {
                rowVariant = field[row][row];
            } else if(rowVariant != 0 && rowVariant != field[row][row] &&
                    any(players, field[row][row])) {
                checkLeftDiagonal = true;
            }

            if(colVariant == 0 && any(players, field[row][field.length - 1 - row])) {
                colVariant = field[row][field.length - 1 - row];
            } else if(colVariant != 0 && colVariant != field[row][field.length - 1 - row] &&
                    any(players, field[row][field.length - 1 - row])) {
                checkRightDiagonal = true;
            }

            checkDiagonals = checkDiagonals && checkLeftDiagonal && checkRightDiagonal;
        }

        return checkCols && checkRows && checkDiagonals;
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
        boolean posExist = field[newSymbPos[0]][newSymbPos[1]] != ' ' ? true : false;

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
