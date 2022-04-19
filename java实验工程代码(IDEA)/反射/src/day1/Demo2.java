package day1;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("day1.Student");
        //基本数据类型也可以.class获取字节码对象
//        Constructor<?> con = c.getConstructor(String.class, int.class, String.class);
//        Object obj = con.newInstance("张三", 11, "北京");
//        System.out.println(obj);

        // 测试私有构造方法(报错)
//         private  Student(String name){
//            this.name = name;
//        }
//        Constructor<?> c1 = c.getDeclaredConstructor(String.class);
//        System.out.println(c1.newInstance("我"));


        Constructor<?> con = c.getDeclaredConstructor(String.class);
        //解决办法: 暴力反射
        //public void setAccessible(boolean flag)将此反射对象的accessible标志设置为指示的布尔值。
        // 值为true表示反射对象应该在使用Java语言访问控制时抑制检查。
        // 值为false表示反射对象应该在使用Java语言访问控制时执行检查，并在类描述中指出变体。
        con.setAccessible(true);
        Object obj = con.newInstance("我");
        System.out.println(obj);


    }
}
