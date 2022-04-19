package day01;

public class LambdaDemo {
    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println("多线程启动了");
//        }).start();


        //匿名内部类
        useInter(new Inter() {
            @Override
            public void show() {
                System.out.println("接口");
            }
        });
        //lambda表达式
        useInter(() -> System.out.println("lambda"));


        //匿名内部类
        useAnimal(new Animal() {
            @Override
            public void menthod() {
                System.out.println("lambda转换的目标类型必须是接口");
            }
        });
        //lambda转换的目标类型必须是接口，下面报错
        //useAnimal(()-> System.out.println("lambda"));


        //匿名内部类
        //lambda表达式



    }

    public static void useAnimal(Animal animal) {
        animal.menthod();
    }

    public static void useInter(Inter inter) {
        inter.show();
    }

    public static void useStudent(Student student) {
        student.study();
    }
}
