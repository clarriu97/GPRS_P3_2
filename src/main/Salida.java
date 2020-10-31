package main;

import java.util.ArrayList;
import java.util.List;

public class Salida {

    private List<Event> listaSalidas;
    private String nombreArchivoSalida;

    public Salida(String nombreArchivoSalida){
        listaSalidas = new ArrayList<>();
        this.nombreArchivoSalida = nombreArchivoSalida;
    }

    public void add(Event event){
        listaSalidas.add(event);
    }

    public void finishProcesing() {
        //TODO: make the output file with the events info
        for (Event event: listaSalidas){
            int acepted = 0;
            if (!event.isAcepted()){ acepted = 1;}
            java.lang.System.out.println(event.getTiempoLlegada() + " " + event.getTiempoServicio() + " " + acepted);
        }
    }
}
