/**
 * @author Kanishka.................
 */

package server;

import shared.SharedQueue;
import java.util.logging.Logger;

public class ServerThread implements Runnable{

    private SharedQueue queue = null;
    private final static Logger LOGGER = Logger.getLogger(ServerThread.class.getName());
    private static final int SLEEP = 4000;

    public ServerThread(SharedQueue resource){
        this.queue = resource;
    }

    @Override
    public void run() {
        while(true) {
            if (this.queue.createdQueue().size() > 0) {
                try {
                    Thread.sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int number = 0;
                try {
                    String take = this.queue.createdQueue().take();
                    number = Integer.parseInt(take.replaceAll("[^0-9]", ""));
                    LOGGER.info("Server thread reading from queue ------ REQUEST -> RESPONSE ----- " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (this.queue.isRequestFlagComplete() && (number) == this.queue.getQueueCapacity()) {
                    LOGGER.warning("MAX REQUEST LIMIT REACHED SERVER SHUTTING DOWN <---------------------------->");
                    System.exit(0);
                }
            }
        }

    }








}
