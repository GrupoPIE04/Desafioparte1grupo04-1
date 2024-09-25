import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        System.out.print("Enter the order (random, ascending, descending): ");
        String order = scanner.next();

        System.out.print("Allow repeating numbers? (yes/no): ");
        String allowRepeats = scanner.next();

        int[] array = generateArray(n, order, allowRepeats.equalsIgnoreCase("yes"));

        System.out.println("Unsorted array: " + Arrays.toString(array));

        long startTime = System.nanoTime();
        mergeSort(array, 0, array.length - 1);
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
                int temp = array[i];
                array[i] = array[randomIndex];
                array[randomIndex] = temp;
            }
        }

        return array;
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            merge(array, left, middle, right);
        }
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, middle + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
