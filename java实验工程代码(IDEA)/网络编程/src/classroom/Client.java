package classroom;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        //指定接收地址和端口号
//        System.out.println(InetAddress.getLocalHost());
        Socket socket = new Socket(InetAddress.getByName("172.20.21.195"), 10086);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String line;
        while ((line = br.readLine()) != null) {
            if(line.equals("886")) {
                break;
            }
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
