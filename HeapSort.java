import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HeapSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements to sort: ");
        int n = scanner.nextInt();

        System.out.print("Enter the order (1 for random, 2 for descending, 3 for ascending): ");
        int order = scanner.nextInt();

        System.out.print("Allow repeating numbers? (true/false): ");
        boolean allowRepeats = scanner.nextBoolean();

        int[] array = generateArray(n, order, allowRepeats);

        System.out.println("Original array: " + Arrays.toString(array));

        long startTime = System.nanoTime();
        heapSort(array);
        long endTime = System.nanoTime();

        System.out.println("Sorted array: " + Arrays.toString(array));
        System.out.println("Running time: " + (endTime - startTime) + " nanoseconds");
    }

    private static int[] generateArray(int n, int order, boolean allowRepeats) {
        Random random = new Random();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = allowRepeats ? random.nextInt(n) : i;
        }

        if (order == 2) {
            Arrays.sort(array);
            for (int i = 0; i < n / 2; i++) {
                int temp = array[i];
                array[i] = array[n - i - 1];
                array[n - i - 1] = temp;
            }
        } else if (order == 3) {
            Arrays.sort(array);
        } else if (order == 1 && !allowRepeats) {
            shuffleArray(array);
        }

        return array;
    }

    private static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }
}