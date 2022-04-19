package test2;

public class PrintableDemo {
    public static void main(String[] args) {
        rectAngle r = new rectAngle(2, 2, 5, 6);
        r.printWay('c');

        eqRectAngle e = new eqRectAngle(3, 3, 7);
        e.printWay('*');
    }
}
