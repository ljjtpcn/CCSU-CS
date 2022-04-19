package day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Student> c1 = Student.class;
        System.out.println(c1);

        Class<Student> c2 = Student.class;
        System.out.println(c1 == c2); // true

        Student s = new Student();
        Class<? extends Student> c3 = s.getClass();
        System.out.println(c1 == c3); //true

        Class<?> c4 = Class.forName("day1.Student");
        System.out.println(c1 == c4); //ture


        /*
            反射获取构造方法并使用
         */
        Class<?> c = Class.forName("day1.Student");
        //Constructor<?>[] cons = c.getConstructors(); //只能找到公共的构造方法
        Constructor<?>[] cons = c.getDeclaredConstructors();
        for(Constructor con : cons){
            System.out.println(con);
        }
        System.out.println("-------------");

        Constructor<?> con = c.getDeclaredConstructor();
        System.out.println(con);

        Object obj = con.newInstance();
        System.out.println(obj );


    }
}
