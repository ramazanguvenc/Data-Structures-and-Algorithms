package Q4;

public class Main {
    public static void main(String[] args){
        Integer [] n = new Integer [10];
        Sorts<Integer> tmp  =new Sorts<>();
        n[0] = 100; n[1] = 200; n[2] = 500 ; n[3] = 1000; n[4] = 2000;
        n[5] = 3000; n[6] = 4000; n[7] = 5000; n[8] = 6000; n[9] = 7000;
        for(int i =0; i < 10 ; i++){
            Integer [] input = new Integer[n[i]];
            for(int j = 0 ; j < n[i] ; j++)
                input[j] = (int) Math.round(Math.random() * 10000);
            long x = System.nanoTime();
            //insertionSortu diger kisimlara elle degistirdim.
            tmp.insertionSort(input);
            long y = System.nanoTime() - x;
            System.out.println(y + " nanoseconds");
        }
    }
}
