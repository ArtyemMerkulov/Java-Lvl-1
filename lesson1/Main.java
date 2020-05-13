package homework.lesson1;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задание 2: ");
        secondTask();
        System.out.println("Задание 3: ");
        System.out.println(thirdTask(1, 2, 3, 4));
        System.out.println("Задание 4: ");
        System.out.println(fourthTask(5, 3));
        System.out.println("Задание 5: ");
        fifthTask(-4);
        System.out.println("Задание 6: ");
        System.out.println(sixthTask(0));
        System.out.println("Задание 7: ");
        seventhTask("Петя");
        System.out.println("Задание 8: ");
        eighthTask("2020");
    }

    static void secondTask() {
        System.out.println("Целые числа:");

        byte byteVar = -128;
        short shortVar = 32767;
        int intVar = 2147483647;
        long longVar = -9223372036854775808L;

        System.out.println(byteVar);
        System.out.println(shortVar);
        System.out.println(intVar);
        System.out.println(longVar);

        System.out.println("Вещественные числа:");

        float floatVar = 3.4e+38f;
        double doubleVar = 4.9e-324;

        System.out.println(floatVar);
        System.out.println(doubleVar);

        System.out.println("Логический тип:");

        boolean boolVar = true;

        System.out.println(boolVar);

        System.out.println("Символьный тип:");

        char charVar = 32767;

        System.out.println(charVar);
    }

    static double thirdTask(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    static boolean fourthTask(int firstNumber, int secondNumber) {
        final boolean res = ((firstNumber + secondNumber) >= 10 && (firstNumber + secondNumber) <= 20)? true : false;
        return res;
    }

    static void fifthTask (int number) {
        if(number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    static boolean sixthTask(int number) {
        final boolean res = number < 0? true : false;
        return res;
    }

    static void seventhTask(String name) {
        System.out.println("Привет, " + name + "!");
    }

    static void eighthTask(String year) {
        int intYear = Integer.parseInt(year);

        if(intYear % 4 == 0 && intYear % 100 != 0 || intYear % 400 == 0) {
            System.out.println(year + " год является високосным.");
        } else {
            System.out.println(year + " год не является високосным.");
        }
    }
}
