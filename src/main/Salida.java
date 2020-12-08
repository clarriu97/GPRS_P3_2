package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        if (nombreArchivoSalida == null){ nombreArchivoSalida = "archivoDeSalida.txt"; }

        try {
            File salida = new File(nombreArchivoSalida);
            if (salida.createNewFile()){
                FileWriter writer = new FileWriter(nombreArchivoSalida);
                for (Event event: listaSalidas){
                    writer.write(Double.toString(event.getTiempoLlegada()) + " ");
                    if (event.isPremium()){ writer.write("premium");} else { writer.write("basico");}
                    if (event.isReentrada()){ writer.write("1");} else { writer.write("0");}
                    writer.write(Double.toString(event.getTiempoServicio()) + " " +
                            Integer.toString(event.getCpd()) + " ");
                    writer.write(event.getAcepted());

                }
            } else {

            }
        } catch (IOException e){

        }

        for (Event event: listaSalidas){
            java.lang.System.out.println(event.getTiempoLlegada() + " " + event.getTiempoServicio() + " " + event.getAcepted());
        }
    }
}
