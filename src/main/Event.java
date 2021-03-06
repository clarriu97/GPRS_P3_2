package main;

public class Event implements Comparable<Event> {

    private boolean premium, reentrada;
    private double tiempoLlegada, tiempoServicio, tiempoSalida;
    private int cpd, acepted;

    public Event(boolean premium, double tiempoLlegada, double tiempoServicio) {
        this.premium = premium;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoServicio = tiempoServicio;
        reentrada = false;
        tiempoSalida = tiempoLlegada + tiempoServicio;
        acepted = 0;
        cpd = 0;
    }

    public void print(){
        java.lang.System.out.print("Tipo: " + premium + "\n" +
                "Tiempo de llegada: " + Double.toString(tiempoLlegada) + "\n" +
                "Tiempo de servicio: " + Double.toString(tiempoServicio) + "\n" +
                "Reentrada: " + reentrada + "\n\n");
    }

    public Double getTiempoLlegada(){
        return tiempoLlegada;
    }

    public double getTiempoSalida() {
        return tiempoSalida;
    }

    public double getTiempoServicio() {
        return tiempoServicio;
    }

    @Override
    public int compareTo(Event event) {
        return Double.compare(tiempoLlegada, event.getTiempoLlegada());
    }

    public void setAcepted(int acepted) {
        this.acepted = acepted;
    }

    public int getAcepted(){return acepted;}



    public void setTiempoSalida(Double tiempoSalida){
        this.tiempoSalida = tiempoSalida;
    }

    public void setTiempoServicio(Double tiempoServicio){ this.tiempoServicio = tiempoServicio;}

    public void setReentrada(boolean reentrada){this.reentrada = reentrada;}

    public boolean isReentrada(){ return reentrada;}

    public boolean isPremium(){ return premium;}

    public int getCpd() { return cpd; }

    public void setCpd(int cpd) { this.cpd = cpd; }
}
