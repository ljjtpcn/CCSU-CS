package window;

import javax.swing.*;
import java.awt.*;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/27 17:13
 */

public class BackgoundPan  extends JPanel {
    private Image backIcon;
    public BackgoundPan(Image backIcon) {
        this.backIcon = backIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
