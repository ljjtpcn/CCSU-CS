package day3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //创建服务端套接字对象并指定端口号
        ServerSocket ss = new ServerSocket(10086);

        //启动监听
        Socket accept = ss.accept();

        //调用输入流读取数据并写入本地文件
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java实验\\网络编程\\data.txt"));
        String s;
        while((s = br.readLine()) != null){
            bw.write(s);
            bw.newLine();
            bw.flush();
        }

        //向客户端发送反馈
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        writer.write("文件上传成功！");
        writer.newLine();
        writer.flush();

        bw.close();
        ss.close();

    }
}
