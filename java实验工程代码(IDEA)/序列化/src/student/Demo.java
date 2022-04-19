package student;

import java.io.*;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        try {
            File file = new File("./序列化/src/student/save.txt");
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Student student = (Student) ois.readObject();

                System.out.println("×××学生基本信息×××");
                System.out.println("编号     姓名     身份证");
                StringBuilder sb = new StringBuilder();
                sb.append(student.getId()).append("    ").append(student.getName()).append("   ").append(student.getCard());
                System.out.println(sb);
                System.out.println();
                System.out.println("×××学生成绩×××");
                System.out.println("科目      成绩");
                System.out.println("Java     " + student.getJava());
                System.out.println("Sql      " + student.getSql());
                System.out.println("Jsp      " + student.getJsp());
                System.out.println("总成绩    " + student.sum());
            } else {
                Student st;
                Scanner sc = new Scanner(System.in);
                String id, name, card;
                double java, sql, jsp;
                System.out.println("********输入学生信息********");
                System.out.print("学生编号:");
                id = sc.next();
                System.out.print("学生姓名:");
                name = sc.next();
                System.out.print("学生身份证:");
                card = sc.next();
                System.out.print("Java成绩:");
                java = sc.nextDouble();
                System.out.print("Sql成绩:");
                sql = sc.nextDouble();
                System.out.print("Jsp成绩:");
                jsp = sc.nextDouble();

                st = new Student(id, name, card, java, sql, jsp);
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(st);
                fos.close();
                oos.close();
                System.out.println("信息已保存！");
            }
        } catch (Exception e) {
            System.out.println("保存失败！请重新运行程序！");
            e.printStackTrace();
        }
    }
}
