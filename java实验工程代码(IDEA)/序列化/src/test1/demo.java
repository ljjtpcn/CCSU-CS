package test1;

import java.io.*;

public class demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        user usr = new user("张三", 11);
//        System.out.println(usr);
//        user[] usrs = {new user("aa", 1), new user("bb", 1)};
        System.out.println("-------------------");


        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("out.txt"));
//        oos.writeObject(usrs);
        oos.writeObject(new user("aa", 1));
        oos.close();

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("out.txt"));
//        Object o = ois.readObject();
//        System.out.println(o);

//        user o = (user) ois.readObject();
//        System.out.println(o);

//        Object[] os = (Object[]) ois.readObject();
////        for(Object o :                    os){
////            System.out.println(o);
////        }
        ois.close();
    }
}
