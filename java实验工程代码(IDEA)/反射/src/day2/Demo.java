package day2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //加载数据
        Properties prop = new Properties();
        FileReader fr = new FileReader("D:\\java实验\\反射\\class.txt");
        prop.load(fr);
        fr.close();

        /*
            className=day2.Student
            methodName=show
         */
        String className = prop.getProperty("className");
        String methodName = prop.getProperty("methodName");

        //反射
        Class<?> c = Class.forName(className);
        Constructor<?> con = c.getConstructor();
        Object obj = con.newInstance();


        Method method = c.getMethod(methodName);
        method.invoke(obj);
    }
}

