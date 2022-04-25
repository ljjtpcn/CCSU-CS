package classroom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10086);
        Socket accept = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/ljjtpcn/Desktop/work/CS学科/java实验工程代码(IDEA)/网络编程/src/classroom/config.imi"));

        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        bufferedWriter.write("上传成功！");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.close();
        bw.close();
        br.close();
    }
}
