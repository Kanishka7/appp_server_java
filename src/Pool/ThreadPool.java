package Pool;

import client.ClientThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class ThreadPool {

    private int threshold;
    ExecutorService executor = null;
    private final static Logger LOGGER = Logger.getLogger(ThreadPool.class.getName());
    private static ThreadPool pool = null;


    private  ThreadPool(){
        LOGGER.info("Initializing Single Thread Pool <------->");
    }

    public static ThreadPool createSinglePool(){
        if (pool == null) {
            pool = new ThreadPool();
        }
        return pool;
    }

    public ExecutorService executePoolService(){
        if (this.executor == null) {
            this.executor = Executors.newFixedThreadPool(this.getThreadThreshold());
        }
        return this.executor;
    }

    public void setThreadThreshold(int count){
        this.threshold = count;
    }

    public int getThreadThreshold(){
        return this.threshold;
    }


}
