package basic;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        //创建一个配置类对象
        Point model = new Point();

        //加载配置
        Properties properties = new Properties();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("basic/config.properties");
        properties.load(in);

        //获取配置类的Class对象对应的所有方法
        Method[] methods = Point.class.getMethods();

        for (Method method : methods) {
            //获取方法名
            String methodName = method.getName();

            //判断方法名是否以set开头
            if (methodName.startsWith("set")) {
                //获取属性第一个字母
                String firstName = methodName.substring(3, 4).toLowerCase();
                //获取属性第一个后字母
                String lastName = methodName.substring(4);
                //属性名
                String newName = firstName + lastName;
                //获取配置文件中newName对应的属性值
                String property = properties.getProperty(newName);

                if (!Objects.isNull(property)) {
                    //获取方法参数类型列表
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    String sn = parameterTypes[0].getSimpleName();

                    //判断属于哪种类型
                    Object arg = switch (sn) {
                        case "int", "Integer" -> Integer.parseInt(property);
                        case "long", "Long" -> Long.parseLong(property);
                        case "double", "Double" -> Double.parseDouble(property);
                        case "float", "Float" -> Float.parseFloat(property);
                        case "boolean", "Boolean" -> Boolean.parseBoolean(property);
                        case "String" -> property;
                        default -> null;
                    };
                    //反射赋值
                    method.invoke(model, arg);
                }
            }
        }

        //测试输出
        System.out.println(model);
    }

}
