/**
 * @author Kanishka.................
 */
package main;

import Pool.ThreadPool;
import client.ClientThread;
import server.ServerThread;
import shared.SharedQueue;

import java.util.logging.Logger;


public class MainThread {

    private final static Logger LOGGER = Logger.getLogger(MainThread.class.getName());
    private static final int MIN = 1;
    public static void main(String []args){
        LOGGER.info("Main Thread started ---");
        try {
            if (Integer.parseInt(args[0]) > MIN) {
                SharedQueue sharedQueue = SharedQueue.getSharedQueue();
                sharedQueue.setQueueCapacity(Integer.parseInt(args[0]));
                ClientThread client = new ClientThread(sharedQueue);
                ServerThread server = new ServerThread(sharedQueue);
                ThreadPool sharedPool = ThreadPool.createSinglePool();
                sharedPool.setThreadThreshold(Integer.parseInt(args[0]));
                sharedPool.executePoolService().execute(server);
                sharedPool.executePoolService().execute(client);
            }  else {
                LOGGER.warning("Minimum Thread number required is 2 : java -jar application_server.jar 2");
                System.exit(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.warning("Run the Jar file by appending the number of threads parameter at the end as : java -jar application_server.jar 10");
            System.exit(0);
        }

    }











}
