package book;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-11-27 10:25
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
class Thread1 extends Thread{
    public Thread1(String name){
        super(name);
    }
    @Override
    public void run(){
        for (int i = 0; i < 1000; i ++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

public class Demo3 {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1("线程1");
        Thread1 t2 = new Thread1("线程2");
        t1.start();
        t2.start();
        System.out.println("当前活动的线程数为:" + Thread.activeCount());
    }
}
