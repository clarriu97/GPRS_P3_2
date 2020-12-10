package main;

import java.io.*;
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
                writeToFile();
            } else {
                salida.delete();
                writeToFile();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        for (Event event: listaSalidas){
            java.lang.System.out.println(event.getTiempoLlegada() + " " + event.getTiempoServicio() + " " + event.getAcepted());
        }
    }

    private void writeToFile(){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(nombreArchivoSalida);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Event event: listaSalidas){
                if (!event.isReentrada()){
                    printWriter.printf(Double.toString(event.getTiempoLlegada()) + " ");
                    if (event.isPremium()){ fileWriter.write("premium ");} else { fileWriter.write("basico ");}
                    printWriter.printf("0 ");
                    printWriter.printf(Double.toString(event.getTiempoServicio()) + " ");
                    if (event.getCpd() == 0){ printWriter.printf("- "); } else {printWriter.printf(Integer.toString(event.getCpd()) + " ");}
                    printWriter.printf(Integer.toString(event.getAcepted()) + "\n");
                }
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
