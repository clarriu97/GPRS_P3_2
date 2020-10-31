package main;

public class Separador {

    private Double paramRetorno;

    public Separador(Double paramRetorno) {
        this.paramRetorno = paramRetorno;
    }

    public boolean checkRetorno(){
        return Math.random() < paramRetorno;
    }
}
