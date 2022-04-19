package day1;

public class MyThreadDemo {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();

        //设置线程名称
        mt1.setName("aaaaa");
        mt2.setName("bbbbb");
        //获取线程名称
        System.out.println(mt1.getPriority());
        mt1.start();
        mt2.start();
    }
}
