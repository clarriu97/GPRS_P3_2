package main;

import java.util.ArrayList;
import java.util.List;

public class SyncQueue<Event> {

    private List<main.Event> list;
    private int maxSize;

    public SyncQueue(int maxSize) {
        list = new ArrayList<main.Event>();
        this.maxSize = maxSize;
    }

    synchronized public int size() {
        return list.size();
    }

    synchronized public boolean isEmpty() {
        return list.isEmpty();
    }

    synchronized public void clear() {
        list.clear();
    }

    public boolean isFull(){
        return list.size() == maxSize;
    }

    synchronized public void put(main.Event e) {
        list.add(e);
    }

    public main.Event get() {
        main.Event event = list.get(0);
        list.remove(0);
        return event;
    }

}
