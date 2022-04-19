package day1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class SendDemo {
    public static void main(String[] args) throws IOException {
//        InetAddress address = InetAddress.getByName("你爹");
//        System.out.println(address.getHostName());
//        System.out.println(address.getHostAddress());

        //创建发送端socket对象
        DatagramSocket ds = new DatagramSocket();

        //键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = br.readLine()) != null){
            if("886".equals(s)){
                break;
            }

            //打包发送
            byte[] bys = s.getBytes();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("count"), 10086);

            //发送
            ds.send(dp);
        }

        //关闭
        ds.close();
    }
}
