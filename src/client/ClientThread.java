package client;

import shared.SharedQueue;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientThread implements Runnable {

    private final static Logger LOGGER = Logger.getLogger(ClientThread.class.getName());
    private SharedQueue queue = null;
    private volatile int counter = 0;
    public ClientThread(SharedQueue resource){
        this.queue = resource;
    }


    @Override
    public void run() {
        while (true){
            try {

                if (this.counter <  this.queue.getQueueCapacity()) {
                    LOGGER.info("Client thread adding to request to queue----REQUEST -- "+(++this.counter));
                    String value = "Queue First --" + Integer.toString(this.counter);
                    this.queue.createdQueue().put(value);
                } else {
                    if (!this.queue.isRequestFlagComplete()){
                        LOGGER.warning("MAX REQUEST LIMIT REACHED CLIENT SHUTTING DOWN <---->");
                        this.queue.setIsRequestDone(true);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }







}
