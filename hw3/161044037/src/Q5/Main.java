package Q5;

public class Main {
    public static void main(String[] args){
        //Worst case icin sadece insertion sort ve quick sort icin yazdim.
        //Cunku digerlerinin worst caselik bir durumu yok.
        //Quick sort icin sorted bir array insertion sort icin tersten sorted array hazirladim.
        Integer [] n = new Integer [4];
        n[0] = 100; n[1] = 1000; n[2] = 5000; n[3] = 10000;
        Sorts<Integer> tmp = new Sorts<>();
        System.out.println("Quicksort");
        for(int i =0;i<4;i++){
            Integer [] arr = new Integer[n[i]];
            for(int j =0;j<n[i];j++)
                arr[j] = j;
            long x = System.nanoTime();
            tmp.quickSort(arr);
            long y = System.nanoTime() - x;
            System.out.println(y + " nanoseconds");
        }
        System.out.println("\nInsertionsort");
        for(int  i = 0 ; i < 4; i++){
            Integer [] arr = new Integer[n[i]];
            for(int j =0,x = n[i];j<n[i];j++,x--)
                arr[j] = x;
            long x = System.nanoTime();
            tmp.insertionSort(arr);
            long y = System.nanoTime() - x;
            System.out.println(y + " nanoseconds");
        }
    }
}
