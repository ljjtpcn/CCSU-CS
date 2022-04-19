package functionInterfac;

public class Demo {
    public static void main(String[] args) {
        FunInter fi = () -> System.out.println("java");
//        fi.show();

        useRunnable(() -> System.out.println(Thread.currentThread().getName() + "启动"));
    }

    public static void useRunnable(Runnable r) {
        new Thread(r).start();

    }
}
