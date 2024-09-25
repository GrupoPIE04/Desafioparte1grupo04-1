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

    private static int medianOfThree(int[] array, int low, int mid, int high) {
        if (array[low] > array[mid]) {
            swap(array, low, mid);
        }
        if (array[low] > array[high]) {
            swap(array, low, high);
        }
        if (array[mid] > array[high]) {
            swap(array, mid, high);
        }
        swap(array, mid, high - 1);
        return array[high - 1];
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
    } else if (order.equalsIgnoreCase("random")) {
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
    int mid = low + (high - low) / 2;
    int pivot = medianOfThree(array, low, mid, high);
    int i = low;
    int j = high - 1;

    while (true) {
        while (i < high - 1 && array[++i] < pivot) {}
        while (j > low && array[--j] > pivot) {}
        if (i >= j) {
            break;
        }
        swap(array, i, j);
    }
    swap(array, i, high - 1);
    return i;
}
}

