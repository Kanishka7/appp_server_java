package main;

import Pool.ThreadPool;
import client.ClientThread;
import server.ServerThread;
import shared.SharedQueue;

import java.util.logging.Logger;

public class MainThread {

    private final static Logger LOGGER = Logger.getLogger(MainThread.class.getName());

    public static void main(String []args){

        LOGGER.info("Main Thread started ---");
        SharedQueue sharedQueue = SharedQueue.getSharedQueue();
        sharedQueue.setQueueCapacity(10);
        ClientThread client = new ClientThread(sharedQueue);
        ServerThread server = new ServerThread(sharedQueue);
        ThreadPool sharedPool = ThreadPool.createSinglePool();
        sharedPool.setThreadThreshold(10);
        sharedPool.executePoolService().execute(server);
        sharedPool.executePoolService().execute(client);


    }











}
