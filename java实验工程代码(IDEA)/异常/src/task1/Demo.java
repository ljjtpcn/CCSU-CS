package task1;

import java.io.File;
import java.io.IOException;

public class Demo {
    public static void main(String[] args){

        //创建file对象，指定文件夹路径
        File file = new File("D:\\java实验\\异常\\test");

        //判断文夹是否存在，不存在则新建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        //在文件夹下自动创建10个文件,依次为a_4.txt, a_1.txt,…, a_9.txt
        for (int i = 0; i <= 9; i++) {
            StringBuffer sb = new StringBuffer("D:\\java实验\\异常\\test\\");
            String s = sb.append("a_").append(i).append(".txt").toString();
            try{
                File file1 = new File(s);
                if(file1.createNewFile()){
                    System.out.println("文件" + s + "创建成功！");
                } else {
                    System.out.println("出错了,该文件已经存在。");
                }

            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
