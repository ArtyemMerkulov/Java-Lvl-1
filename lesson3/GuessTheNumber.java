import java.util.Arrays;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        int EXIT_CODE = -1;
        int ERROR_CODE = -2;
        int initValue = -10; // Нельзя вводить числа большие -3
        int minValue = 0; // Нельзя вводить отрицательные числа
        int maxValue = 100; // Нельзя вводить отрицательные числа
        int winner = 0; // Нельзя вводить числа большие 0
        String greeting = "\nЗдравствуйте, {0}! Пожалуйста, введите " +
                "команду 'СТОП', если Вы\nхотите прекратить игру или введите " +
                "целое число от " + minValue + " до " + maxValue + ": ";

        int answer = getRandomIntInclusive(minValue, maxValue); // Загадываем число из интервала [minValue, maxValue]

        System.out.print("Пожалуйста, введите число игроков: ");
        int nPlayers = getNPlayers(); // Получаем количество игроков

        int[] usersAnswers = new int[nPlayers];
        initUsersAnswers(usersAnswers, initValue); // Задаем начальные значения ответов игроков

        String[] usersNames = getUsersNames(nPlayers); // Получаем имена игроков

        while(winner == 0 && !all(usersAnswers, EXIT_CODE)) { // Играем до тех пор пока нет победителя или все игроки не покинули игру
            for(int iPlayer = 0; iPlayer < nPlayers; iPlayer++) { // Перебираем игроков
                String iGreeting = greeting.replace("{0}", usersNames[iPlayer]); // Инициализация приветствия для игрока

                if(usersAnswers[iPlayer] != EXIT_CODE) { // Если игрок не покинул игру:
                    do {
                        usersAnswers[iPlayer] = playRound(iGreeting, answer,
                                minValue, maxValue, EXIT_CODE, ERROR_CODE); // Ход игрока
                    } while (usersAnswers[iPlayer] == ERROR_CODE); // заставляем игрока делать ходы до тех пор, пока он не введет корректную команду ("стоп" или число из интервала)
                }

                if(usersAnswers[iPlayer] == answer) { // если игрок угадал число
                    winner = iPlayer + 1; // запоминаем порядковый номер победившего игрока
                    break; // заканчиваем игру, т. к. число угадано и дальше играть нет смысла
                }
            }
        }

        if(winner > 0) { // Если есть победитель, то выводим сообщение с поздравлением
            System.out.println("\nИгра окончена! Победил Игрок №" + winner +
                    " - " + usersNames[winner - 1] + "!");
        } else { // Иначе выводим сообщение о том, чтовсе игроки покинули игру
            System.out.println("\nИгра окончена! Все игроки покинули игру!");
        }
    }

    public static boolean all(int[] arr, int value) {
        for(int element : arr) {
            if(element != value) {
                return false;
            }
        }

        return true;
    }

    public static int getNPlayers() {
        Scanner nPlayersScan = new Scanner(System.in);
        return nPlayersScan.nextInt();
    }

    private static String[] getUsersNames(int nPlayers) {
        String[] usersNames = new String[nPlayers];
        Scanner usersNamesScan = new Scanner(System.in);

        for(int iPlayer = 0; iPlayer < nPlayers; iPlayer++) {
            System.out.print("Пожалуйста, введите имя Игрока №" + (iPlayer + 1) + ": ");
            usersNames[iPlayer] = usersNamesScan.nextLine();
        }

        return usersNames;
    }

    public static void initUsersAnswers(int[] usersAnswers, int initValue) {
        Arrays.fill(usersAnswers, initValue);
    }

    public static int getRandomIntInclusive(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);

        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    public static int playRound(String greeting, int answer, int minValue,
                                int maxValue, int exitCode, int errorCode) {
        Scanner in = new Scanner(System.in);
        System.out.print(greeting);
        String userCommand = in.nextLine().toLowerCase();

        int userAnswer = checkCommand(userCommand, answer, minValue,
                maxValue, exitCode, errorCode);

        return userAnswer;
    }

    public static int checkCommand(String userCommand, int answer, int minValue,
                                   int maxValue, int exitCode, int errorCode) {
        if(!userCommand.equals("") && Character.isDigit(userCommand.charAt(0))) {
            int userAnswer = Integer.parseInt(userCommand);

            if(userAnswer >= minValue && userAnswer <= maxValue) {
                if (userAnswer > answer) {
                    System.out.println("Вы ввели слишком большое число! " +
                            "Попробуйте еще раз!");
                } else if (userAnswer < answer) {
                    System.out.println("Вы ввели слишком маленькое число! " +
                            "Попробуйте еще раз!");
                } else {
                    System.out.println("Поздравляем! Вы угадали число " + answer + "!");
                }

                return userAnswer;
            } else {
                System.out.println("Вы ввели число, выходящие за пределы загаданного " +
                        "интервала [" + minValue + ", " + maxValue + "].");
                return errorCode;
            }
        } else if(userCommand.equals("стоп")) {
            System.out.println("Вы вышли из игры.");
            return exitCode;
        } else {
            System.out.println("Вы ввели некоректную команду.");
            return errorCode;
        }
    }
}