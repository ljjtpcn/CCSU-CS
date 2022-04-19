package fanxing;

import java.util.ArrayList;

public class testPoint {
    public static void main(String[] args) {
        System.out.println(new Point<>(10, 10).pringPoint());
        System.out.println(new Point<>(12.88, 129.65).pringPoint());
        System.out.println(new Point<>("东京180度", "北纬210度").pringPoint());

        ArrayList<String> strings = new ArrayList<>();
    }
}
