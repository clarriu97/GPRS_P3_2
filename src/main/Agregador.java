package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agregador {

    private List<Event> eventList;

    public Agregador(List<Event> premiumList, List<Event> basicosList){
        eventList = new ArrayList<>();
        for (Event event: premiumList){ eventList.add(event);}
        for (Event event: basicosList){ eventList.add(event);}
        Collections.sort(eventList);
    }

    public void addEvent(Event event){
        eventList.add(event);
        Collections.sort(eventList);
    }

    public Event getInminentEvent(boolean remove){
        if (remove){
            Event event = eventList.get(0);
            eventList.remove(0);
            return event;
        }
        return eventList.get(0);
    }

    public boolean isEmpty(){
        return eventList.isEmpty();
    }

    public void print(){
        if (!eventList.isEmpty()){
            for (Event event: eventList){
                event.print();
            }
        }
    }
}
