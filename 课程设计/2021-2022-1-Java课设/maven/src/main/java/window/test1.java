package window;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/30 14:23
 */

public class test1 {


    public static void main(String[] args) {
        int[] a = new int[8];
        int j;
        for (j = 1; j < a.length; j++) {
            a[j] = a[j-1] + 2;
            System.out.print(a[j-1] + ",");

        }
        System.out.println(a[j-1]);

        Complex c = new Complex(2, 3);
        Complex c1 = new Complex(2, 0);
        Complex c2 = new Complex(0, -3);
        System.out.println(c);

        System.out.println(c1);
        System.out.println(c2);
        Math.abs(-1);

        if(1){
             String.valueOf(1);
        }else{

        }
    }


}


class Complex{

    double real;
    double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(real  != 0 && imaginary !=0){
            sb.append(real).append("+").append(imaginary).append("i");
        }
        if(imaginary == 0 && real != 0){
            sb.append(real);
        }
        if(real == 0 && imaginary != 0){
            sb.append(imaginary).append("i");
        }
        return sb.toString();
    }
}
