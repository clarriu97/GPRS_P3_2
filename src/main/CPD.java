package main;

import java.util.List;

public class CPD {

    private Event[] processors;
    private SyncQueue queue;
    private Salida salida;
    private double clock;

    // Constructor
    public CPD(int numProcessors, int queueSize) {
        processors = new Event[numProcessors];
        for (int i = 0; i<numProcessors; i++){
            processors[i] = null;
        }
        queue = new SyncQueue(queueSize);
        salida = new Salida();
        clock = 0.000000;
    }

    //Main function, where we check if an important event has occured
    //and we do something in that case
    public void process(Double clock){
        this.clock = clock;
        while (somethingToProcess()){

            //If an event in the processors has ended, we process that processor event
            for (int i = 0; i<processors.length; i++){
                if (processors[i] != null){
                    if (clock >= processors[i].getTiempoSalida()){
                        processProcessorEvent(processors[i], i);
                    }
                }
            }

            //If there is an event in the queue, we check if there is an empty space in the processors
            //if there is a free processor, we process that queue event
            if (!queue.isEmpty()){
                if (!processorsAreFull()){
                    processQueueEvent();
                }
            }

            //If there is an event to process in the FEL, we process it
            /*if (!fel.isEmpty()){
                if (clock >= fel.getInminentEvent(false).getTiempoLlegada()){
                    processFELEvent(fel.getInminentEvent(true));
                }
            }*/

        }

        //Method called when there are no more events to be processed
        finishProcesing();
    }

    //Method for checking if there are any event to be processed in the hole system
    private boolean somethingToProcess() {
        //if (!fel.isEmpty()){ return true;}
        if (!queue.isEmpty()){ return true;}
        if (!processorsAreEmpty()){ return true;}
        return false;
    }

    //Method to process an event which is in the FEL
    //if there is space in the processors, we send the event to a processor
    //if not, we check if there is space in the queue, if yes we send it to the queue
    //finally, if there is not space in the processors or the queue, we mark the event
    //as not acepted and we send it to Salida
    private void processFELEvent(Event event){
        if (!processorsAreFull()){
            processors[getFreeProcessor()] = event;
        } else if (!queue.isFull()){
            queue.put(event);
        } else {
            event.setAcepted(false);
            salida.add(event);
        }
    }

    //Method to process an event which is in the queue
    //we only call this method if we are sure that there is an event in the queue and
    //there is at least one empty space in the processors, so the only thing it does is
    //to get the event from the FIFO queue, set the new tiempoSalida and send if to
    //an empty space of the processors
    private void processQueueEvent(){
        Event event = queue.get();
        event.setTiempoSalida(clock + event.getTiempoServicio());
        processors[getFreeProcessor()] = event;
    }


    //Method to process an event whick is in the processors
    //we only call this method if we know that an event from the processors has ended and
    //we receive the position which will come up free, so we add that event to the Salida
    //and we set the position as null
    private void processProcessorEvent(Event event, int freePos){
        salida.add(event);
        processors[freePos] = null;
    }

    //Method to check if ALL processors are empty
    private boolean processorsAreEmpty(){
        boolean empty = true;
        for (int i = 0; i<processors.length; i++){
            if (processors[i] != null){
                empty = false;
            }
        }
        return empty;
    }

    //Method to check if ALL processors are processing an event
    private boolean processorsAreFull(){
        boolean full = true;
        for (int i = 0; i<processors.length; i++){
            if (processors[i] == null){
                full = false;
            }
        }
        return full;
    }

    //Method that gives back an int with the position of the first empty processor
    //which is free
    private int getFreeProcessor(){
        int freeProcessor = -1;
        for (int i = 0; i<processors.length; i++){
            if (processors[i] == null){
                freeProcessor = i;
                break;
            }
        }
        return freeProcessor;
    }

    //Method called when there are no more events in the system
    //we tell Salida to finish the task
    private void finishProcesing(){
        salida.finishProcesing();
    }

    public void addEventToProcessors(Event event){
        processors[getFreeProcessor()] = event;
    }

    public void addEventToQueue(Event event){
        queue.put(event);
    }

    public boolean queueIsFull(){
        return queue.isFull();
    }

}
