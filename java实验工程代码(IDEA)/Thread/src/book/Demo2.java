package book;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-11-27 10:20
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

class MyThread extends Thread{
    @Override
    public void run(){
        while(true){
            System.out.println("run");
        }
    }
}
public class Demo2 {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();
        while (true){
            System.out.println("main");
        }
    }
}
