package test1;

import java.io.*;
import java.util.Scanner;

public class a {
    public static void main(String[] args) throws IOException {
        Scanner in =new Scanner(System.in);
        int age=in.nextInt();
        stu num=new stu();
        num.setting(age);
        System.out.println(num.getting());
    }
}