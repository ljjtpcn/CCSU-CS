package day3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServerDemo {
    public static void main(String[] args) throws IOException {
        //创建服务端套接字对象并指定端口号
        ServerSocket ss = new ServerSocket(10086);

        while(true){
            //启动监听
            Socket accept = ss.accept();
            new Thread(new ServerThread(accept)).start();
        }



    }

}
