package task3;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        int idx = 2;
        while (idx-- > 0) {
            int score = sc.nextInt();
            Teacher teacher = new Teacher();

            try {
                teacher.test(score);
            } catch (TestException ioe) {
                ioe.printStackTrace();
            }
        }

    }
}
