package test2;

public class eqRectAngle extends rectAngle implements Printable {
    // x、y 为左上角坐标点位置，width、height 为宽和高；
    private int x, y, width, height;

    public eqRectAngle() {
    }

    ;

    public eqRectAngle(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = width;

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
