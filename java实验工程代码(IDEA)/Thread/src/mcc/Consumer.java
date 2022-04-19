package mcc;

/**
 * @author ljjtpcn
 */
public class Consumer implements Runnable {
    public Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        storage.getNumber();
    }
}
