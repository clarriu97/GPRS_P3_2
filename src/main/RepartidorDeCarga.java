package main;

import java.util.ArrayList;
import java.util.List;

public class RepartidorDeCarga {

    private List<Event> eventList;

    public RepartidorDeCarga(){
        eventList = new ArrayList<>();
    }

    public void addEvent(Event event){
        eventList.add(event);
    }



}
