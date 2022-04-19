package consumer;

import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        String[] str = {"alan,18", "李四,5", "张三,4"};

        useConsumer(str, s -> System.out.print("姓名:" + s.split(",")[0]),
                s -> System.out.println("年龄:" + s.split(",")[1]));
    }

    public static void useConsumer(String[] str, Consumer<String> con1, Consumer<String> con2) {
        for (String s : str) {
            con1.andThen(con2).accept(s);
        }
    }
}
