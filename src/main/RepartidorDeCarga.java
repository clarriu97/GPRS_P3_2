package main;


public class RepartidorDeCarga {

    private Event processingEvent;
    private SyncQueue queue;
    private Double tiempoServicio;
    private CPD bloque1, bloque2;
    private boolean eventToSalida;

    public RepartidorDeCarga(Double tiempoServicio, int maxSize, CPD bloque1, CPD bloque2){
        queue = new SyncQueue(maxSize);
        this.tiempoServicio = tiempoServicio;
        processingEvent = null;
        this.bloque1 = bloque1;
        this.bloque2 = bloque2;
        eventToSalida = false;
    }

    public void process(Double clock){
        //if there is processing an event we check if it has passed the tiempoServicio, in that case
        //we send it to the respective CPD or to the Salida
        //in adition, if the queue is not empty, we start processing the first event
        if (isProcessingEvent() && clock >= processingEvent.getTiempoLlegada()+tiempoServicio){

            if (processingEvent.isPremium()){
                if (bloque1.checkSpace()){
                    processingEvent.setTiempoLlegada(clock);
                    processingEvent.setCpd(1);
                    bloque1.addEvent(processingEvent);
                    processingEvent = null;
                } else {
                    if (bloque2.checkSpace()){
                        processingEvent.setTiempoLlegada(clock);
                        processingEvent.setCpd(2);
                        bloque2.addEvent(processingEvent);
                        processingEvent = null;
                    } else {
                        eventToSalida = true;
                    }
                }
            } else {
                if (bloque2.checkSpace()){
                    processingEvent.setTiempoLlegada(clock);
                    processingEvent.setCpd(2);
                    bloque2.addEvent(processingEvent);
                    processingEvent = null;
                } else {
                    eventToSalida = true;
                }
            }

            if (!queue.isEmpty()){
                processingEvent = queue.get();
                processingEvent.setTiempoLlegada(clock);
            }
        }


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

    public boolean eventToSalida(){
        if (eventToSalida){
            eventToSalida = false;
            return true;
        }
        return eventToSalida;
    }

    public Event getEventToSalida(){
        Event event = processingEvent;
        processingEvent = null;
        return event;
    }

}
