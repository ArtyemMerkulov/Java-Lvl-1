package homework.l2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("1. Задать целочисленный массив, состоящий из элементов 0 и 1.\n" +
                "Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].\n" +
                "С помощью цикла и условия заменить 0 на 1, 1 на 0;");

        int[] notInvertedArray = new int[] {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Было:\t" + Arrays.toString(notInvertedArray));
        System.out.println("Стало:\t" + Arrays.toString(invertArray(notInvertedArray)));

        System.out.println("\n2. Задать пустой целочисленный массив размером 8.\n" +
                "С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;");

        int[] arithmeticProgression = getArithmeticProgression(0, 8, 3);
        System.out.println("Стало:\t" + Arrays.toString(arithmeticProgression));

        System.out.println("\n3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]\n" +
                "пройти по нему циклом, и числа меньшие 6 умножить на 2;");

        int[] notModifedArray = new int[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Было:\t" + Arrays.toString(notModifedArray));
        System.out.println("Стало:\t" + Arrays.toString(MulBy2IfLessThen6(notModifedArray)));

        System.out.println("\n4. Создать квадратный двумерный целочисленный массив\n" +
                "(количество строк и столбцов одинаковое), и с помощью\n" +
                "цикла(-ов) заполнить его диагональные элементы единицами;");

        for(int[] matrixRow : getDiagonalMatrix(5, 1)) {
            System.out.println(Arrays.toString(matrixRow));
        }

        System.out.println("\n5. ** Задать одномерный массив и найти в нем минимальный\n" +
                "и максимальный элементы (без помощи интернета);");

        double[] arr = new double[] {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Исходный массив:\t" + Arrays.toString(arr));
        System.out.println("Максимум:\t" + max(arr));
        System.out.println("Минимум:\t" + min(arr));

        System.out.println("\n6. ** Написать метод, в который передается не пустой\n" +
                "одномерный целочисленный массив, метод должен вернуть true,\n" +
                "если в массиве есть место, в котором сумма левой и правой части\n" +
                "массива равны. Примеры:\n" +
                "checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,\n" +
                "checkBalance([1, 1, 1, || 2, 1]) → true, граница показана\n" +
                "символами ||, эти символы в массив не входят.\nСложность O(2n) = O(n)");

        int[] balanceArray = new int[] {2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println("Исходный массив:\t" + Arrays.toString(balanceArray));
        System.out.println("Результат:\t" + checkBalance(balanceArray));

        int[] notBalanceArray = new int[] {2, 2, 2, 1, 2, 2, 1, 10, 1};
        System.out.println("Исходный массив:\t" + Arrays.toString(notBalanceArray));
        System.out.println("Результат:\t" + checkBalance(notBalanceArray));

        System.out.println("\n7. **** Написать метод, которому на вход подается \n" +
                "одномерный массив и число n (может быть положительным, \n" +
                "или отрицательным), при этом метод должен сместить все \n" +
                "элементымассива на n позиций. Для усложнения задачи нельзя \n" +
                "пользоваться вспомогательными массивами.");

        int[] notShiftedArray1 = new int[] {1, 2, 3, 4, 5};
        int[] notShiftedArray2 = new int[] {1, 2, 3, 4, 5, 6};
        int shift = -7;

        System.out.println("Сдвиг = " + shift + ":");

        System.out.println("Было:\t" + Arrays.toString(notShiftedArray1));
        arrayCyclicShift(notShiftedArray1, shift);
        System.out.println("Стало:\t" + Arrays.toString(notShiftedArray1));

        System.out.println("Было:\t" + Arrays.toString(notShiftedArray2));
        arrayCyclicShift(notShiftedArray2, shift);
        System.out.println("Стало:\t" + Arrays.toString(notShiftedArray2));
    }

    /**
     * 1. Задать целочисленный массив, состоящий из элементов 0 и 1.
     * Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0;
     */
    public static int[] invertArray(int[] arr) {
        int[] invertedArray = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            invertedArray[i] = arr[i] ^ 1;
        }

        return invertedArray;
    }

    /**
     * 2. Задать пустой целочисленный массив размером 8.
     * С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    public static int[] getArithmeticProgression(int firstTerm, int sequenceLength,
                                                 int commonDifference) {
        int[] arr = new int[sequenceLength];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = firstTerm + i * commonDifference;
        }

        return arr;
    }

    /**
     * 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
     * пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    public static int[] MulBy2IfLessThen6(int[] arr) {
        int[] res = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            res[i] = arr[i] < 6? arr[i] * 2: arr[i];
        }

        return  res;
    }

    /**
     * 4. Создать квадратный двумерный целочисленный массив
     * (количество строк и столбцов одинаковое), и с помощью
     * цикла(-ов) заполнить его диагональные элементы единицами;
     */
    public static int[][] getDiagonalMatrix(int nRows, int value) {
        int[][] matrix = new int[nRows][nRows];

        for(int row = 0; row < nRows; row++) {
            matrix[row][row] = value;
        }

        return matrix;
    }

    /**
     * 5. ** Задать одномерный массив и найти в нем минимальный
     * и максимальный элементы (без помощи интернета);
     */
    public static double max(double[] arr) {
        double maximum = arr[0];

        for(double value : arr) {
            maximum = maximum < value? value : maximum;
        }

        return maximum;
    }

    public static double min(double[] arr) {
        double minimum = arr[0];

        for(double value : arr) {
            minimum = minimum > value? value : minimum;
        }

        return minimum;
    }

    /**
     * 6. ** Написать метод, в который передается не пустой
     * одномерный целочисленный массив, метод должен вернуть true,
     * если в массиве есть место, в котором сумма левой и правой части
     * массива равны. Примеры:
     * checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
     * checkBalance([1, 1, 1, || 2, 1]) → true, граница показана
     * символами ||, эти символы в массив не входят
     *
     * Сложность O(2n) = O(n)
     */
    public static boolean checkBalance(int[] arr) {
        int leftSum = Arrays.stream(arr).sum();
        int rightSum = 0;

        for(int value : arr) {
            if(leftSum == rightSum) {
                return true;
            }

            leftSum -= value;
            rightSum += value;
        }

        return false;
    }

    /** 7. **** Написать метод, которому на вход подается
     * одномерный массив и число n (может быть положительным,
     * или отрицательным), при этом метод должен сместить все
     * элементымассива на n позиций. Для усложнения задачи нельзя
     * пользоваться вспомогательными массивами.
     */
    public static void arrayCyclicShift(int[] arr, int shift) {
        shift = mod(shift, arr.length);
        int pos = arr.length - shift;

        reverseArray(arr, 0, pos);
        reverseArray(arr, pos, arr.length);
        reverseArray(arr, 0, arr.length);
    }

    public static void reverseArray(int[] arr, int begin, int end) {
        int pos = begin + end;
        for(int i = begin; i < pos / 2; i++) {
            swap(arr, i, pos - i - 1);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int mod(int a, int m) {
        return (a >= 0 || -a == m) ? a % m : m + (a % m);
    }
}