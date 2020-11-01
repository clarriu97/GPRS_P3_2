package main;

import java.util.List;

public class CPD {

    private Event[] processors;
    private SyncQueue queue;

    // Constructor
    public CPD(int numProcessors, int queueSize) {
        processors = new Event[numProcessors];
        for (int i = 0; i<numProcessors; i++){
            processors[i] = null;
        }
        queue = new SyncQueue(queueSize);
    }

    //Main function, where we check if an important event has occured
    //and we do something in that case
    public Event process(Double clock){

        Event event = null;

        //If an event in the processors has ended, we process that processor event
        for (int i = 0; i<processors.length; i++){
            if (processors[i] != null){
                if (clock >= processors[i].getTiempoSalida()){
                    event = processProcessorEvent(processors[i], i);
                }
            }
        }

        //If there is an event in the queue, we check if there is an empty space in the processors
        //if there is a free processor, we process that queue event
        if (!queue.isEmpty()){
            if (!processorsAreFull()){
                processQueueEvent(clock);
            }
        }

        return event;
    }

    //Method for checking if there are any event to be processed in the hole system
    private boolean somethingToProcess() {
        //if (!fel.isEmpty()){ return true;}
        if (!queue.isEmpty()){ return true;}
        if (!processorsAreEmpty()){ return true;}
        return false;
    }

    //Method to process an event which is in the queue
    //we only call this method if we are sure that there is an event in the queue and
    //there is at least one empty space in the processors, so the only thing it does is
    //to get the event from the FIFO queue, set the new tiempoSalida and send if to
    //an empty space of the processors
    private void processQueueEvent(Double clock){
        Event event = queue.get();
        event.setTiempoSalida(clock + event.getTiempoServicio());
        processors[getFreeProcessor()] = event;
    }


    //Method to process an event whick is in the processors
    //we only call this method if we know that an event from the processors has ended and
    //we receive the position which will come up free, so we add that event to the Salida
    //and we set the position as null
    private Event processProcessorEvent(Event event, int freePos){
        processors[freePos] = null;
        return event;
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

    public void addEventToProcessors(Event event){
        processors[getFreeProcessor()] = event;
    }

    public void addEventToQueue(Event event){
        queue.put(event);
    }

    public boolean queueIsFull(){
        return queue.isFull();
    }

    public boolean checkSpace(){
        return !queueIsFull()&&!processorsAreFull();
    }

    public void addEvent(Event event) {
        if (!processorsAreFull()){
            addEventToProcessors(event);
        } else{
            addEventToQueue(event);
        }
    }
}
