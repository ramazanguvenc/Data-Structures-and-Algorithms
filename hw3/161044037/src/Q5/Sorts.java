package Q5;

/**
 * Sorts from book.
 */
public class Sorts<T> {
    public static <T extends Comparable<T>> void insertionSort(T[] table){
        for(int nextPos = 1; nextPos < table.length; nextPos++){
            insert(table,nextPos);
        }
    }

    private static <T extends Comparable<T>> void insert(T[] table,int nextPos){
        T nextVal = table[nextPos];
        while(nextPos > 0 && nextVal.compareTo(table[nextPos - 1]) < 0){
            table[nextPos] = table[nextPos - 1];
            nextPos--;
        }
        table[nextPos] = nextVal;
    }
    public static <T extends Comparable<T>> void heapSort(T[] table) {
        buildHeap(table);
        shrinkHeap(table);
    }

    private static <T extends Comparable<T>> void buildHeap(T[] table) {
        int n = 1;

        while (n < table.length) {
            n++;
            int child = n - 1;
            int parent = (child - 1) / 2; // Find parent.
            while (parent >= 0
                    && table[parent].compareTo(table[child]) < 0) {
                swap(table, parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }
        }
    }

    private static <T extends Comparable<T>> void shrinkHeap(T[] table) {
        int n = table.length;

        while (n > 0) {
            n--;
            swap(table, 0, n);

            int parent = 0;
            while (true) {
                int leftChild = 2 * parent + 1;
                if (leftChild >= n) {
                    break; // No more children.
                }
                int rightChild = leftChild + 1;
                // Find the larger of the two children.
                int maxChild = leftChild;
                if (rightChild < n // There is a right child.
                        && table[leftChild].compareTo(table[rightChild]) < 0) {
                    maxChild = rightChild;
                }

                if (table[parent].compareTo(table[maxChild]) < 0) {
                    // Swap the parent and child.
                    swap(table, parent, maxChild);
                    // Continue at the child level.
                    parent = maxChild;
                } else { // Heap property is restored.
                    break; // Exit the loop.
                }
            }
        }
    }


    public static <T extends Comparable<T>> void mergeSort(T[] table) {
        // A table with one element is sorted already.
        if (table.length > 1) {
            // Split table into halves.
            int halfSize = table.length / 2;
            T[] leftTable = (T[]) new Comparable[halfSize];
            T[] rightTable = (T[]) new Comparable[table.length - halfSize];
            System.arraycopy(table, 0, leftTable, 0, halfSize);
            System.arraycopy(table, halfSize, rightTable, 0,
                    table.length - halfSize);
            // Sort the halves.
            mergeSort(leftTable);
            mergeSort(rightTable);
            // Merge the halves.
            merge(table, leftTable, rightTable);
        }
    }


    private static <T extends Comparable<T>> void merge(T[] outputSequence,
                                                        T[] leftSequence,
                                                        T[] rightSequence) {
        int i = 0;
        // Index into the left input sequence.
        int j = 0;
        // Index into the right input sequence.
        int k = 0;
        // Index into the output sequence.
        // While there is data in both input sequences
        while (i < leftSequence.length && j < rightSequence.length) {
            // Find the smaller and
            // insert it into the output sequence.
            if (leftSequence[i].compareTo(rightSequence[j]) < 0) {
                outputSequence[k++] = leftSequence[i++];
            } else {
                outputSequence[k++] = rightSequence[j++];
            }
        }
        // assert: one of the sequences has more items to copy.
        // Copy remaining input from left sequence into the output.
        while (i < leftSequence.length) {
            outputSequence[k++] = leftSequence[i++];
        }
        // Copy remaining input from right sequence into output.
        while (j < rightSequence.length) {
            outputSequence[k++] = rightSequence[j++];
        }
    }
    public static <T extends Comparable<T>> void quickSort(T[] table) {
        // Sort the whole table.
        quickSort(table, 0, table.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] table,
                                                            int first, int last) {
        if (first < last) { // There is data to be sorted.
            // Partition the table.
            int pivIndex = partition(table, first, last);
            // Sort the left half.
            quickSort(table, first, pivIndex - 1);
            // Sort the right half.
            quickSort(table, pivIndex + 1, last);
        }
    }


    private static <T extends Comparable<T>> int partition(T[] table,
                                                           int first, int last) {
        // Select the first item as the pivot value.
        T pivot = table[first];
        int up = first;
        int down = last;
        do {

            while ((up < last) && (pivot.compareTo(table[up]) >= 0)) {
                up++;
            }
            // assert: up equals last or table[up] > pivot.
            while (pivot.compareTo(table[down]) < 0) {
                down--;
            }
            // assert: down equals first or table[down] <= pivot.
            if (up < down) { // if up is to the left of down.
                // Exchange table[up] and table[down].
                swap(table, up, down);
            }
        } while (up < down); // Repeat while up is left of down.
        // Exchange table[first] and table[down] thus putting the
        // pivot value where it belongs.
        swap(table, first, down);
        // Return the index of the pivot value.
        return down;
    }

    private static <T extends Comparable<T>> void swap(T[] table,int f,int d){
        T temp;
        temp = table[f];
        table[f] = table[d];
        table[d] = temp;
    }

}


