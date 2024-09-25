import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        System.out.print("Enter the order (random, ascending, descending): ");
        String order = scanner.next();

        System.out.print("Allow repeating numbers? (yes/no): ");
        String allowRepeats = scanner.next();

        int[] array = generateArray(n, order, allowRepeats.equalsIgnoreCase("yes"));

        System.out.println("Original array: " + Arrays.toString(array));

        long startTime = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();

        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Running time: " + (endTime - startTime) + " nanoseconds");
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
                int temp = array[randomIndex];
                array[randomIndex] = array[i];
                array[i] = temp;
            }
        }

        return array;
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}