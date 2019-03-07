package Q2;

public class Main {
    public static void main(String[] args){
        RecursiveHashSet<Integer> tmp1 = new RecursiveHashSet<>();
        for(int i =0;i<21;i++)
            tmp1.add(i);
        System.out.println("Degerler eklendi Set basiliyor");
        tmp1.printSet();
        System.out.println("------------------");
        System.out.println("0,3,20 degerleri silinip set tekrardan basilacak");
        tmp1.remove(0);
        tmp1.remove(3);
        tmp1.remove(20);
        tmp1.printSet();
        System.out.println(tmp1.contains(2)+ " contains deneniyor.");
        System.out.println(tmp1.contains(3)+ " contains deneniyor.");
        System.out.println("-----------------------------\n\n\\n");
        RecursiveHashSet<String> tmp2 = new RecursiveHashSet<>();
        tmp1.add("Rida");
        tmp1.add("Jessica");
        tmp1.add("mary");
        tmp1.printSet();
        tmp1.remove("Rida");
        tmp1.printSet();
        System.out.println(tmp1.contains("Jessica"));
        System.out.println(tmp1.contains("ramazan"));
    }
}
