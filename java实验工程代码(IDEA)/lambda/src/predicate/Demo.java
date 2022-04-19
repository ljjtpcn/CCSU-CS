package predicate;

import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {

        System.out.println(usePredicate("aaabbb", s -> s.contains("a")
                , s -> s.contains("c")));
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "  " + i);
            }
        });
        thread1.start();
        thread2.start();
    }

    public static boolean usePredicate(String s, Predicate<String> pre1, Predicate<String> pre2){
        return pre1.and(pre2).test(s);
    }
}
