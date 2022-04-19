package day1;

public class MyThread extends Thread{
    public MyThread(){

    };

    @Override
    public void run() {
        for(int i = 1; i <= 100; i ++){
            System.out.println(i);
        }
    }
}
