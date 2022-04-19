package window;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.scene.shape.Circle;
import org.junit.Test;
import service.impl.GraphicalServiceimpl;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/25 11:28
 */

public class AddViewTest {

    @Test
    public void testAddView(){
        new AddView(new MainView("111", "test"));
    }


    public static void main(String[] args) {
        int[] a = new int[8];
        int j;
        for (j = 0; j < a.length; j++) {
            a[j] = a[j-1] + 2;
            System.out.print(a[j-1] + ",");

        }
        System.out.println(a[j-1]);
    }


}


