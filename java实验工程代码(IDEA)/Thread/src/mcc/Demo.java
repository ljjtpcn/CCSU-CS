package mcc;

import java.util.ArrayList;

/**
 * @author ljjtpcn
 */
public class Demo {
    public static void main(String[] args) {
//        Storage pools = new Storage(new ArrayList<>(), 2, 20);
//
//        Thread t1 = new Thread(new Productor(pools));
//        Thread t2 = new Thread(new Consumer(pools));
//        t1.setName("。。。生产者线程。。。");
//        t2.setName("。。。消费者线程。。。");
//
//        t1.start();
//        t2.start();

        Integer a = 12, b = 12;
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));

    }
}

