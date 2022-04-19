package task2;

import java.io.File;

public class Demo {
    public static void main(String[] args) {

        File file = new File("D:\\java实验\\异常\\test");
        if(!file.exists()){
            file.mkdir();
        }

        String[] list = file.list();
        System.out.println("文件夹内文件数目为:" + list.length);
    }
}
