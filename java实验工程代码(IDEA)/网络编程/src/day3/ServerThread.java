package day3;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket s;

    public ServerThread(Socket accept) {
        this.s = accept;
    }

    @Override
    public void run() {
        //调用输入流读取数据并写入本地文件
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\java实验\\网络编程\\data.txt"));

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            //向客户端发送反馈
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            writer.write("文件上传成功！");
            writer.newLine();
            writer.flush();

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
