package book;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-11-27 10:51
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */

class TickerWindowss implements Runnable {
    public int tickets = 50;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "正在发售第" + (50 - (--tickets)) + "张票");
            } else {
                break;
            }
        }
    }
}

public class Demo6 {
    public static void main(String[] args) {
        TickerWindowss t = new TickerWindowss();
        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口1");
        t1.start();
        t2.start();

    }
}

