package student;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class test {
    public static void main(String []args) throws Exception {
        //1.创建User用户对象
        User user = new User("tsgz","012345","铁扇公主");

        //2.创建低级的字节输出流通向目标文件
        System.out.println(test.class.getClassLoader().getName());

        //3.把低级的字节输出流包装成高级的对象字节输出流ObjectOutputStream
//        ObjectOutputStream oos =new ObjectOutputStream(fos);
//
//        //4.通过对象字节输出流序列化对象
//        oos.writeObject(user);
//
//        //6.释放资源
//        oos.close();
//
//        System.out.println("序列化对象成功");


    }
}
