package book;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-11-27 10:31
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class Thread3 extends Thread{
    public Thread3(String name){
        super(name);
    }
    @Override
    public void run(){
        for (int i = 0; i < 5; i ++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}


public class Demo4 {
    public static void main(String[] args) {
        Thread2 t2 = new Thread2();
        Thread thread = new Thread(t2, "线程1");
        Thread3 t3 = new Thread3("线程2");
        thread.start();
        t3.start();
        System.out.println("当前活动的线程数为:" + Thread.activeCount());

    }
}
