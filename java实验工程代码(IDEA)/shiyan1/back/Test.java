package basic;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("basic/config.properties");
        pro.load(in);

        String classname = pro.getProperty("classname");
        String method = pro.getProperty("method");

        String argss = pro.getProperty("args");
        String argsType = pro.getProperty("argsType");

        String[] argsList = argss.split(",");
        String[] argsTypeList = argsType.split(",");

        Class<?> c1 = Class.forName(classname);

        Constructor<?> con = c1.getConstructor();
        Object obj1 = con.newInstance();



        Method method1 = c1.getMethod(method, int.class, int.class, String.class);
        int x = Integer.parseInt(argsList[0]);
        int y = Integer.parseInt(argsList[1]);
        String name = argsList[2];

        method1.invoke(obj1, x, y, name);
        System.out.println(obj1);


        Class<?> c2 = Class.forName(classname);
        Constructor<?> con2 = c2.getConstructor(int.class, int.class, String.class);
        Object obj2 = con2.newInstance(1, 2, "dada");
        System.out.println(obj2);

    }
}
