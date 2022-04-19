package mcc;

/**
 * @author ljjtpcn
 */
public class Productor implements Runnable {

    public Storage storage;

    public Productor(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {

        storage.setNumber();
    }
}
