import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        System.out.print("Enter the order (random, ascending, descending): ");
        String order = scanner.next();

        System.out.print("Allow repeating numbers? (yes/no): ");
        boolean allowRepeats = scanner.next().equalsIgnoreCase("yes");

        int[] array = generateArray(n, order, allowRepeats);

        System.out.println("Unsorted array: " + Arrays.toString(array));

        long startTime = System.nanoTime();
        insertionSort(array);
        long endTime = System.nanoTime();

        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Running time: " + (endTime - startTime) + " nanoseconds");
    }

    private static int[] generateArray(int n, String order, boolean allowRepeats) {
        Random random = new Random();
        int[] array = new int[n];

        if (allowRepeats) {
            for (int i = 0; i < n; i++) {
                array[i] = random.nextInt(100);
            }
        } else {
            for (int i = 0; i < n; i++) {
                array[i] = i;
            }
            shuffleArray(array, random);
        }

        if (order.equalsIgnoreCase("ascending")) {
            Arrays.sort(array);
        } else if (order.equalsIgnoreCase("descending")) {
            Arrays.sort(array);
            for (int i = 0; i < n / 2; i++) {
                int temp = array[i];
                array[i] = array[n - i - 1];
                array[n - i - 1] = temp;
            }
        }

        return array;
    }

    private static void shuffleArray(int[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
    
