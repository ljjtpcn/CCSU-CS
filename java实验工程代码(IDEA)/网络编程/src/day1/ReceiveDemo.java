package day1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveDemo {
    public static void main(String[] args) throws IOException {
        //创建数据接收端对象，端口为10086
        DatagramSocket ds = new DatagramSocket(10086);

        while (true) {
            //创建数据打包对象
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);

            //开始接收
            ds.receive(dp);

//        byte[] data = dp.getData();
//        int len = dp.getLength();
//        System.out.println(new String(data, 0, len));
            //解包输出
            System.out.println(new String(dp.getData(), 0, dp.getLength()));
        }
    }
}
