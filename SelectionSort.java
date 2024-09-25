import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SelectionSort {

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
        selectionSort(array);
        long endTime = System.nanoTime();

        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Sorting time: " + (endTime - startTime) + " nanoseconds");
    }

    private static int[] generateArray(int n, String order, boolean allowRepeats) {
        Random random = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = allowRepeats ? random.nextInt(n) : i;
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
        } else if (order.equalsIgnoreCase("random") && !allowRepeats) {
            for (int i = 0; i < n; i++) {
                int randomIndex = random.nextInt(n);
                int temp = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = temp;
            }
        }

        return array;
    }

    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
