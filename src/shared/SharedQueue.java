/**
 * @author Kanishka.................
 */

package shared;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

public class SharedQueue {

    private final static Logger LOGGER = Logger.getLogger(SharedQueue.class.getName());
    private static SharedQueue sharedQueue;
    private ArrayBlockingQueue<String> transport = null;
    private int capacity = 0;
    private volatile boolean isRequestDone = false;

private SharedQueue(){
}

public synchronized void setIsRequestDone(boolean flag){
    this.isRequestDone = flag;
}

public boolean isRequestFlagComplete(){
    return this.isRequestDone;
}

public static SharedQueue getSharedQueue(){
    if (sharedQueue == null) {
        sharedQueue = new SharedQueue();
    }

    return sharedQueue;
}


public void setQueueCapacity(int capacity){
    this.capacity = capacity;
}

public int getQueueCapacity(){
    return this.capacity;
}

public synchronized ArrayBlockingQueue<String> createdQueue(){
    if (this.transport == null) {
        LOGGER.info("TRANSPORT STREAM CREATED  --- CONNECTION SUCCESSFUL");
        this.transport = new ArrayBlockingQueue<>(this.getQueueCapacity());
    }
    return this.transport;
}











}
