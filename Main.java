package Task5_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentHashMap<>(0, 0.75f);
        Map<Integer,Integer>hashMap=new HashMap<>(0,0.75f);
        long element = 1000000;
        int countThread = 10;
        writeData(map, element, countThread);
        readData(map, element, countThread);

        //Work with HashMap
        writeData(hashMap, element, countThread);
        readData(hashMap, element, countThread);
    }

    public static void writeData(Map<Integer, Integer> map, long element, int countThread) {
        long time = System.currentTimeMillis();
        Thread[] threads = new Thread[countThread];
        for (int i = 0; i < countThread; i++) {
            threads[i] = new Thread(new InputDataInMap(map, element));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(map.getClass() + " : " + "time work : " + (System.currentTimeMillis() - time) + " millisecond");
    }

    public static void readData(Map<Integer, Integer> map, long element, int countThread) {
        long time = System.currentTimeMillis();
        Thread[] threads = new Thread[countThread];
        for (int i = 0; i < countThread; i++) {
            threads[i] = new Thread(new OutDataFromMap(map, element));
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(map.getClass() + " : " + "time work : " + (System.currentTimeMillis() - time) + " millisecond");
    }

}
