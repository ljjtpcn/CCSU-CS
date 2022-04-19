package window;

import org.junit.Test;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/25 12:11
 */

public class VisualizationLineViewTest {
    @Test
    public void testVisualizationLineView(){
        new VisualizationLineView(null, new MainView("test", "test"));
    }
}
