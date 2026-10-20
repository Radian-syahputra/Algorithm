public class BubbleSort {

    // Method untuk mengurutkan array secara ascending
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Tukar posisi
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // Jika tidak ada pertukaran, array sudah terurut
            if (!swapped) {
                break;
            }
        }
    }

    // Method untuk mencetak array
    static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Sebelum diurutkan:");
        printArray(data);

        bubbleSort(data);

        System.out.println("Setelah diurutkan:");
        printArray(data);
    }
}