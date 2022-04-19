package window;

import org.junit.Test;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/25 11:42
 */

public class VisualizationRadarViewTest {

    @Test
    public void testVisualizationRadarView(){
        new VisualizationRadarView(null, new MainView("111", "test"));
    }
}
