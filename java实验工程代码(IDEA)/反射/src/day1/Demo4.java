package day1;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Demo4 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> c = Class.forName("day1.Student");
        Constructor<?> con = c.getConstructor();
        Object obj = con.newInstance();

        Field name = c.getDeclaredField("name");
        name.setAccessible(true) ;
        name.set(obj, "张三");

        Field age = c.getDeclaredField("age");
        age.set(obj, 18);

        Field address = c.getField("address");
        address.set(obj, "北京");

        System.out.println(obj );
    }
}
