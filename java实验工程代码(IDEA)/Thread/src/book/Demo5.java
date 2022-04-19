package book;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-11-27 10:39
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
class TickerWindows extends Thread {
    public int tickets = 50;

    public TickerWindows(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
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

public class Demo5 {
    public static void main(String[] args) {
        TickerWindows t1 = new TickerWindows("窗口1");
        TickerWindows t2 = new TickerWindows("窗口2");
        t1.start();
        t2.start();

    }
}
