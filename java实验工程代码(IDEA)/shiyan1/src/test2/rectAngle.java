package test2;

import java.util.Collection;
import java.util.Collections;

public class rectAngle implements Printable {
    // x、y 为左上角坐标点位置，width、height 为宽和高；
    private int x, y, width, height;

    public rectAngle() {
    }

    public rectAngle(int w, int h) {
        width = w;
        height = h;
    }

    public rectAngle(int X, int Y, int w, int h) {
        this(w, h);
        x = X;
        y = Y;
    }

    public rectAngle(rectAngle r) {
        this(r.width, r.height);
        x = r.x;
        y = r.y;
    }

    // 请完成计算周长的方法
    public int calLength() {
        return (width + height) << 1;
    }

    // 请完成计算面积的方法
    public int calArea() {
        return width * height;
    }

    @Override
    public void printWay() {
        System.out.println("实现了");
    }

    @Override
    public void printWay(char c) {
        for (int i = 0; i < height; i++) {
            if (i == 0 || i == height - 1) {
                for (int j = 0; j < width; j++) {
                    System.out.print(c);

                }


            } else {
                for (int j = 0; j < width; j++) {
                    if (j == 0 || j == width - 1) {
                        System.out.print(c);
                    } else {
                        System.out.print(' ');
                    }


                }

            }
            System.out.println();
        }
    }
}
