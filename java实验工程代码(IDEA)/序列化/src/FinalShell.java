import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author 吾爱论坛 ljjtpcn@163.com
 * @date [2021/12/1 15:29]
 * @description TODO FinalShell离线激活
 */
public class FinalShell {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.print("请输入FinalShell的离线机器码：");
        Scanner reader = new Scanner(System.in);
        String machineCode = reader.nextLine();
        generateKey(machineCode);
    }

    public static void generateKey(String hardwareId) throws NoSuchAlgorithmException {
        String proKey = transform(61305 + hardwareId + 8552);
        String pfKey = transform(2356 + hardwareId + 13593);
        System.out.println("请将此行复制到离线激活中："+proKey);
        System.out.println(pfKey);
    }

    public static String transform(String str) throws NoSuchAlgorithmException {

        String md5 = hashMD5(str);

        return hashMD5(str).substring(8, 24);
    }

    public static String hashMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashed = digest.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashed) {
            int len = b & 0xFF;
            if (len < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(len));
        }
        return sb.toString();
    }
}
