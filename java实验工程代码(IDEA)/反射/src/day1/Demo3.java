package day1;

import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Demo3 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> c = Class.forName("day1.Student");

        //获取公共属性
//        Field[] fields = c.getFields();
//        for (Field field : fields){
//            System.out.println(field);
//        }

        //获取全部属性
        Field[] declaredFields = c.getDeclaredFields();
        for(Field field : declaredFields){
            System.out.println(field);
        }
        System.out.println("---------------------");

        //获得无参构造方法
        Constructor<?> con = c.getConstructor();
        //无参构造创建对象
        Object obj = con.newInstance();
        //获取单个公共属性,非公共属性用getDeclaredField
        Field addres = c.getField("address");
        //set方法设置address属性值
        addres.set(obj, "北京 ");
        System.out.println(obj);
    }
}
