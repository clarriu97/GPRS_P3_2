package main;

import java.util.List;

public class System {

    private Agregador agregador;
    private Salida salida;
    private RepartidorDeCarga repartidorDeCarga;
    private CPD bloque1, bloque2;
    private Double clock;
    private Separador separador;

    public System(List<Event> premiumList, List<Event> basicList, Double tiempoServicioBloque0, int maxColaBloque0, int numServidoresCPD1,
                  int maxColaCPD1, int numServidoresCPD2, int maxColaCPD2, Double paramRetorno, String nombreArchivoSalida){

        agregador = new Agregador(premiumList, basicList);

        repartidorDeCarga = new RepartidorDeCarga(tiempoServicioBloque0, maxColaBloque0);
        bloque1 = new CPD(numServidoresCPD1, maxColaCPD1);
        bloque2 = new CPD(numServidoresCPD2, maxColaCPD2);

        separador = new Separador(paramRetorno);
        salida = new Salida(nombreArchivoSalida);

        clock = 0.000000;
    }

    //Main function
    public void start(){
        while (notFinished()){
            Event event1, event2 = null;
            event1 = bloque1.process(clock);
            event2 = bloque2.process(clock);

            //If an event goes out from bloque1 or bloque2, it will be not null, in that case we check if it
            //goes in again to the system through agregador1, or it will go to the Salida
            if (event1 != null){
                if (separador.checkRetorno()){
                    event1.setTiempoServicio(event1.getTiempoServicio()/2);
                    event1.setTiempoLlegada(clock);
                    agregador.addEvent(event1);
                } else { salida.add(event1);}
            }
            if (event2 != null){
                if (separador.checkRetorno()){
                    event2.setTiempoServicio(event2.getTiempoServicio()/2);
                    event2.setTiempoLlegada(clock);
                    agregador.addEvent(event2);
                } else { salida.add(event2);}
            }

            //Here we check if an event has arrived, in that case we send it to the repartidorDeCarga if it is not full
            //if it is full we sent it directly to the Salida
            if (clock >= agregador.getInminentEvent(false).getTiempoLlegada()){
                Event event = agregador.getInminentEvent(true);
                if (repartidorDeCarga.isSpaceInQueue() || !repartidorDeCarga.isProcessingEvent()){
                    repartidorDeCarga.addEvent(event);
                } else {
                    salida.add(event);
                }
            }

            repartidorDeCarga.process(clock);

            clock += 0.000001;
        }
    }

    private boolean notFinished(){
        return true;
    }

}
