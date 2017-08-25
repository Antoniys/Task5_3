package Task5_3;

import java.util.HashMap;
import java.util.Map;

public class InputDataInMap implements Runnable {
    private Map map;
    private long element;

    public InputDataInMap(Map myMap, long element) {
        this.map = myMap;
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

    public void workWithSynchronizedMap() {
        for (Integer i = 0; i < element; i++) {
            synchronized (map) {
                map.put(i, i);
                map.notifyAll();
            }
        }
    }

    public void workWithMap() {
        for (Integer i = 0; i < element; i++) {
            map.put(i, i);
        }
    }
}

