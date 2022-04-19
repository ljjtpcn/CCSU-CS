package day2;

public class Demo {
    public static void main(String[] args) {
        //lambda表达式
        //useInter(s -> System.out.println(s));

        //方法引用符 ::
        //表示引用System.out对象中的println方法
        //useInter(System.out::println);
        //可推导的就是可省略的
        useInter(Integer::parseInt);
    }
    public static void useInter(Inter i){
        System.out.println(i.show("999"));
    }
}
