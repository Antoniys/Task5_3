package Task5_3;

import java.util.HashMap;
import java.util.Map;

public class OutDataFromMap implements Runnable {
    private Map map;
    private long element;

    public OutDataFromMap(Map myMap, long element) {
        map = myMap;
        this.element = element;
    }

    @Override
    public void run() {
        if(map instanceof HashMap){
            workWithSynchronizedMap();
        }
        else{
            workWithMap();
        }
    }

    public void workWithMap() {
        for (Integer i = 0; i < element; i++) {
            map.get(i);
        }
    }

    public void workWithSynchronizedMap() {
        for (Integer i = 0; i < element; i++) {
            synchronized (map) {
                while (map.get(i) == null) {
                    try {
                        map.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                map.get(i);
                }
            }
        }
    }
}

