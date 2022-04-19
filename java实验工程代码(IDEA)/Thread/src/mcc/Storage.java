package mcc;


import java.util.List;

/**
 * @author ljjtpcn
 */

public class Storage {
    /**
     * 生产池
     */
    private List<Integer> queue;
    /**
     * 一次只能放ｎ数据
     */
    private Integer n;
    /**
     * 当前产了多少数据了
     */
    private Integer current = 0;
    /**
     * 生产者只能最多产maxMoney个, 默认为１０个
     */
    private Integer maxMoney = 10;

    public Storage(List<Integer> queue, Integer n) {
        this.queue = queue;
        this.n = n;
    }

    public Storage(List<Integer> queue, Integer n, Integer maxMoney) {
        this.queue = queue;
        this.n = n;
        this.maxMoney = maxMoney;
    }

    public void getNumber() {
        while (this.getCurrent() < this.getMaxMoney()) {
            synchronized (this.getQueue()) {
                while (this.getQueue().isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + "空了...等待生产");
                    try {
                        this.getQueue().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < this.getQueue().size(); i++) {
                    System.out.println(Thread.currentThread().getName() + "取走了 " + this.getQueue().get(i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.getQueue().clear();
                this.getQueue().notify();
            }
        }
        System.out.println("生产者没钱了。。。罢工结束");
    }

    public void setNumber() {
        for (int i = 1; i <= this.getMaxMoney(); i++) {
            synchronized (this.getQueue()) {
                while (this.getQueue().size() == 2) {
                    System.out.println(Thread.currentThread().getName() + "已满...");
                    try {
                        this.getQueue().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.getQueue().add(i);
                this.setCurrent(this.getCurrent() + 1);
                System.out.println(Thread.currentThread().getName() + "生产了　" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.getQueue().notify();
            }
        }
    }

    public List<Integer> getQueue() {
        return queue;
    }


    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }


    public Integer getMaxMoney() {
        return maxMoney;
    }


}
