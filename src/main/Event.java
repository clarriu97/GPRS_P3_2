package main;

public class Event implements Comparable<Event> {

    private boolean premium, reentrada, acepted;
    private double tiempoLlegada, tiempoServicio, tiempoSalida;
    private int cpd;

    public Event(boolean premium, double tiempoLlegada, double tiempoServicio) {
        this.premium = premium;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoServicio = tiempoServicio;
        reentrada = false;
        tiempoSalida = tiempoLlegada + tiempoServicio;
        acepted = true;
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

    public void setAcepted(boolean acepted) {
        this.acepted = acepted;
    }

    public boolean isAcepted(){return acepted;}

    public void setTiempoLlegada(double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
        tiempoSalida = tiempoLlegada + tiempoServicio;
    }

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
