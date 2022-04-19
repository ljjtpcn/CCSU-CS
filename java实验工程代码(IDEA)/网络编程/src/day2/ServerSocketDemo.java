package day2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) throws IOException {
        //设置服务器端口
        ServerSocket ss = new ServerSocket(11126);
        //启动监听
        Socket accept = ss.accept();
        //调用输入流读取数据
        // InputStream is = accept.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        //byte[] bys = new byte[1024];

        BufferedWriter bw = new BufferedWriter(new FileWriter("网络编程\\data.txt", true));
        String line;
        while((line = br.readLine()) != null){
            //System.out.println(line);
            //写入文本文件
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        /**
         * //反馈给客户端
         *         OutputStream os = accept.getOutputStream();
         *         os.write("我已收到数据".getBytes());
         *
         */
        ss.close();
        bw.close();
    }
}
