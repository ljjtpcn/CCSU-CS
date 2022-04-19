package day3;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //创建套接字对象绑定接收ip，端口
        Socket s = new Socket(InetAddress.getByName("你爹"), 10086);

        //从文件读数据
        BufferedReader br = new BufferedReader(new FileReader("D:\\java实验\\网络编程\\网络编程.iml"));
        //写入流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line;
        while((line = br.readLine()) != null){
            bw.write(line);
            bw.newLine();
            bw.flush();

        }
        //停止发送
        s.shutdownOutput();

        //等待服务器端回应
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(reader.readLine());

        br.close();
        s.close();
    }
}
