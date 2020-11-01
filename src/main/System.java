package main;

import java.util.List;

public class System {

    private Agregador agregador1, agregador2;
    private Salida salida;
    private RepartidorDeCarga repartidorDeCarga;
    private CPD bloque1, bloque2;
    private Double clock;
    private Separador separador;

    public System(List<Event> premiumList, List<Event> basicList, Double tiempoServicioBloque0, int maxColaBloque0, int numServidoresCPD1,
                  int maxColaCPD1, int numServidoresCPD2, int maxColaCPD2, Double paramRetorno, String nombreArchivoSalida){

        agregador1 = new Agregador(premiumList, basicList);
        agregador2 = new Agregador();

        repartidorDeCarga = new RepartidorDeCarga(tiempoServicioBloque0, maxColaBloque0);
        bloque1 = new CPD(numServidoresCPD1, maxColaCPD1);
        bloque2 = new CPD(numServidoresCPD2, maxColaCPD2);

        separador = new Separador(paramRetorno);
        salida = new Salida(nombreArchivoSalida);

        clock = 0.000000;
    }

    public void start(){


        clock += 0.000001;
    }

}
