import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * The Main class serves as the entry point for the application.
 * Currently, it does not contain any methods or functionality.
 **/
public class Bubblesort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the order of numbers:");
        System.out.println("1. Random order");
        System.out.println("2. Descending order");
        System.out.println("3. Ascending order");
        int orderChoice = scanner.nextInt();

        System.out.println("Enter the number of elements to sort:");
        int n = scanner.nextInt();

        System.out.println("Allow repeating numbers? (yes/no):");
        boolean allowRepeats = scanner.next().equalsIgnoreCase("yes");

        int[] array = generateArray(n, orderChoice, allowRepeats);

        System.out.println("Unsorted array: " + Arrays.toString(array));
        writeArrayToFile(array, "unsorted.txt");

        long startTime = System.nanoTime();
        bubbleSort(array);
        long endTime = System.nanoTime();

        System.out.println("Sorted array: " + Arrays.toString(array));
        writeArrayToFile(array, "sorted.txt");
        printExecutionTime(startTime, endTime);

        scanner.close();
    }

    private static int[] generateArray(int n, int orderChoice, boolean allowRepeats) {
        Random random = new Random();
        int[] array = new int[n];

        if (orderChoice == 1) { // Random order
            for (int i = 0; i < n; i++) {
                array[i] = allowRepeats ? random.nextInt(n) : i;
            }
            if (!allowRepeats) {
                shuffleArray(array);
            }
        } else if (orderChoice == 2) { // Descending order
            for (int i = 0; i < n; i++) {
                array[i] = n - i;
            }
        } else if (orderChoice == 3) { // Ascending order
            for (int i = 0; i < n; i++) {
                array[i] = i + 1;
            }
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

    private static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static void writeArrayToFile(int[] array, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (int num : array) {
                writer.println(num);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + fileName);
        }
    }

    private static void printExecutionTime(long startTime, long endTime) {
        long duration = endTime - startTime;
        System.out.println("Execution time: " + duration + " nanoseconds");
    }
}
    
