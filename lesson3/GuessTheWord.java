import java.util.Scanner;

public class GuessTheWord {

    public static void main(String[] args) {
        int maskLength = 15;
        String[] words = {"apple", "orange", "lemon", "banana", "apricot",
                "avocado", "broccoli", "carrot", "cherry", "garlic", "grape",
                "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        String userAnswer;
        String guessWord = guessTheWord(words);
        String currentAnswer = new String(getNZeroBytes(guessWord.length()));

        do {
            System.out.print("Отгадайте слово " +
                    getMaskedString(currentAnswer, maskLength) + ": ");

            userAnswer = getUserAnswer();
            currentAnswer = checkAnswer(guessWord, userAnswer, currentAnswer);
        } while(!isCorrectAnswer(guessWord, currentAnswer));

        System.out.println("Поздравляем! Вы угадали слово \"" + guessWord + "\"!");
    }

    public static String getMaskedString(String str, int maskLength) {
        int maxLength = maskLength > str.length() ? maskLength : str.length();
        char[] maskedString = new char[maxLength];

        for(int i = 0; i < str.length(); i++) {
            maskedString[i] = (str.charAt(i) == '\u0000') ? '#' : str.charAt(i);
        }

        for(int i = str.length(); i < maxLength; i++) {
            maskedString[i] = '#';
        }

        return String.copyValueOf(maskedString);
    }

    public static byte[] getNZeroBytes(int size) {
        byte[] bytes = new byte[size];

        for(int i = 0; i < size; i++) {
            bytes[i] = 0;
        }

        return bytes;
    }

    public static String checkAnswer(String guessWord, String userAnswer,
                                    String currentAnswer) {
        char[] currentAnswerArray = currentAnswer.toCharArray();
        int minLength = guessWord.length() > userAnswer.length() ? userAnswer.length() : guessWord.length();

        for(int iLatter = 0; iLatter < minLength; iLatter++) {
            currentAnswerArray[iLatter] = guessWord.charAt(iLatter) == userAnswer.charAt(iLatter) ? userAnswer.charAt(iLatter) : currentAnswerArray[iLatter];
        }

        return String.copyValueOf(currentAnswerArray);
    }

    public static String guessTheWord(String[] words) {
        int guessWordIdx = getRandomIntInclusive(0, words.length - 1);
        return words[guessWordIdx];
    }

    public static int getRandomIntInclusive(int min, int max) {
        min = (int) Math.ceil(min);
        max = (int) Math.floor(max);

        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    public static String getUserAnswer() {
        Scanner answerScaner = new Scanner(System.in);
        return (answerScaner.nextLine()).toLowerCase();
    }

    public static boolean isCorrectAnswer(String guessWord, String userAnswer) {
        return guessWord.equals(userAnswer);
    }
}