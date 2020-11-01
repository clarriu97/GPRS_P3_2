package main;


public class RepartidorDeCarga {

    private Event processingEvent;
    private SyncQueue queue;
    private Double tiempoServicio;

    public RepartidorDeCarga(Double tiempoServicio, int maxSize){
        queue = new SyncQueue(maxSize);
        this.tiempoServicio = tiempoServicio;
        processingEvent = null;
    }

    public void process(){

    }

    public boolean addEvent(Event event){
        if (!isProcessingEvent()){
            processingEvent = event;
            return true;
        }
        if (isSpaceInQueue()){
            queue.put(event);
            return true;
        }
        return false;
    }

    private boolean isProcessingEvent(){
        return processingEvent==null?false:true;
    }

    private boolean isSpaceInQueue(){
        return !queue.isFull();
    }

}
