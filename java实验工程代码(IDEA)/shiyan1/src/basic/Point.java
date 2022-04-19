package basic;

import java.util.Objects;

public class Point {
    public int x;
    public int y;
    public String name;

    /**
     * 无参构造函数
     * @param []
     * @return []
     * @date [2022/3/14 上午10:19]
     */
    public Point() {
    }

    /**
     * 3参数构造函数
     * @param [int, int, java.lang.String]
     * @return []
     * @date [2022/3/14 上午10:20]
     */
    public Point(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * 两参数构造函数
     * @param [int, int]
     * @return []
     * @date [2022/3/14 上午10:20]
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 一参数构造函数
     * @param [java.lang.String]
     * @return []
     * @date [2022/3/14 上午10:20]
     */
    public Point(String name) {
        this.name = name;
    }


    /**
     * TODO 移动点到新的位置， 水平偏移量是dx， 垂直偏移量是dy， 并命名新的名称
     * @param [int, int, java.lang.String]
     * @return [void]
     * @date [2022/3/14 上午10:22]
     */
    public void move(int dx, int dy, String name){
        x = x + dx;
        y = y + dy;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), name);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }
}
