package Q1;

public class Main {
    public static void main(String[] args) {
        DoubleHashingMap<String,String> tmp = new DoubleHashingMap<>();
        tmp.put("Ramazan","Guvenc123");
        tmp.put("Saadet","Lale");
        tmp.put("Celal","Kaya");
        tmp.put("Tarik","Kilic1");
        System.out.println("Ramazan keyinin ustune tekrar yazicam ve java documentte belirtildigi gibi onceki keyin yani 'Guvenc123' u dondur" +
                "mesi gerekir.");
        System.out.println( "ths = "+tmp.put("Ramazan","Guvenc"));
        System.out.println("-----------------\n");
        System.out.println(tmp.containsKey("Saadet")+" contains kontrol ediliyor");
        System.out.println(tmp.get("Saadet")+" get kontrol ediliyor");
        tmp.put("a","Jessica");
        tmp.put("b","Mary");
        tmp.put("c","Tine");
        System.out.println("Map bastiriliyor");
        tmp.printMap();
        System.out.println("Ramazan keyi siliniyor");
        tmp.remove("Ramazan");
        System.out.println("------------------\n");
        System.out.println("Map tekrardan bastiriliyor");
        tmp.printMap();
        System.out.println("Simdi size i degistirmek icin daha fazla eleman ekleyecegim ve size 2 katina cikicak yani 32 olacak");
        tmp.put("d","e");
        tmp.put("e","f");
        tmp.put("g","rida");
        System.out.println("--------------------\n");
        System.out.println("Size i genisleyen map bastiriliyor\n");
        tmp.printMap();

        System.out.println("Test bitti\n\n");
    }
}
