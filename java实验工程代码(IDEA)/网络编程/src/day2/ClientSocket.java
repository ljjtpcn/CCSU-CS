package day2;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class ClientSocket {
    public static void main(String[] args) throws IOException {
        //指定接收地址和端口号
        Socket s = new Socket(InetAddress.getByName("ljjtpcn"), 11126);

        //调用输出流写数据
        // 键盘录入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        文件读入
//        BufferedReader br = new BufferedReader(new FileReader("/home/ljjtpcn/Desktop/work/JAVA/java实验/网络编程/网络编程.iml"));
        //封装
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        //OutputStream os = s.getOutputStream();
        //os.write("hello tcp!".getBytes());
        String line;
        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        /**
         * //接收服务器端反馈，调用输入流读数据
         *         InputStream is = s.getInputStream();
         *         byte[] bys = new byte[1024];
         *         String data = new String(bys, 0, is.read(bys));
         *         System.out.println("客户端反馈:" + data);
         */


        //关闭
        s.close();
    }
}
