package main;

import java.util.List;

public class System {

    private String nombreArchivoSalida;
    private Double paramRetorno;
    private Agregador agregador1, agregador2;

    public System(List<Event> premiumList, List<Event> basicList, Double tiempoServicioBloque0, int maxColaBloque0, int numServidoresCPD1,
                  int maxColaCPD1, int numServidoresCPD2, int maxColaCPD2, Double paramRetorno, String nombreArchivoSalida){

        agregador1 = new Agregador(premiumList, basicList);
        agregador2 = new Agregador();

        this.paramRetorno = paramRetorno;
        this.nombreArchivoSalida = nombreArchivoSalida;
    }

    public void start(){

    }

}
