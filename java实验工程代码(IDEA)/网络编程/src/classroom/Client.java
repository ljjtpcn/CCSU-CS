package classroom;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //指定接收地址和端口号
//        System.out.println(InetAddress.getLocalHost());
        Socket socket = new Socket(InetAddress.getByName("count"), 10086);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/ljjtpcn/Desktop/work/CS学科/java实验工程代码(IDEA)/网络编程/src/classroom/data.txt")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        socket.shutdownOutput();

        BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("服务器回应:" + response.readLine());
        response.close();
        br.close();
        bw.close();
    }
}
