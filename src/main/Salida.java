package main;

import java.util.ArrayList;
import java.util.List;

public class Salida {

    private List<Event> listaSalidas;

    public Salida(){
        listaSalidas = new ArrayList<>();
    }

    public void add(Event event){
        listaSalidas.add(event);
    }

    public void finishProcesing() {
        for (Event event: listaSalidas){
            int acepted = 0;
            if (!event.isAcepted()){ acepted = 1;}
            java.lang.System.out.println(event.getTiempoLlegada() + " " + event.getTiempoServicio() + " " + acepted);
        }
    }
}
