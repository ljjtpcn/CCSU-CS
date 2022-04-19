package test2;

import java.util.HashSet;
import java.util.Set;

public class Demo {
    public static void main(String[] args) {

        rectAngle r1  = new rectAngle(1, 2, 3, 4);
        /*r1 采用的构造方法是传入四个参数，它就会选择右边这个构造方法初始化*/

        rectAngle r2  = new rectAngle(r1);
        /*r2 采用的构造方法是传入一个rectAngle对象，它就会选择右边这个构造方法初始化*/
        System.out.println("r1的面积:" + r1.calArea());
        System.out.println("r2的面积:" + r2.calArea());


    }
}
