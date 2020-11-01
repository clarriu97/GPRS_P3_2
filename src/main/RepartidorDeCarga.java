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

    public void process(Double clock){

    }

    public void addEvent(Event event){
        if (!isProcessingEvent()){
            processingEvent = event;
        }
        if (isSpaceInQueue()){
            queue.put(event);
        }
    }

    public boolean isProcessingEvent(){
        return processingEvent==null?false:true;
    }

    public boolean isSpaceInQueue(){
        return !queue.isFull();
    }

}
