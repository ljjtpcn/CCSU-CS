package day1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo5 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Class<?> c = Class.forName("day1.Student");
        Constructor<?> con = c.getConstructor();
        Object obj = con.newInstance();

        //获取成员方法
        Method setAge = c.getDeclaredMethod("setAge", int.class);
        System.out.println(setAge);
        setAge.setAccessible(true);
        setAge.invoke(obj, 14);
        System.out.println(obj);

        Method getAge = c.getDeclaredMethod("getAge");
        Object s = getAge.invoke(obj);
        System.out.println("age:" + s);

    }
}
