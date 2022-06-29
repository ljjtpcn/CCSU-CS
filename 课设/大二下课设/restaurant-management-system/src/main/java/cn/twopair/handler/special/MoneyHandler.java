package cn.twopair.handler.special;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @description:
 * @author: 李佳骏
 * @time: 2021/12/22 9:02
 */

public class MoneyHandler extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {
        int keyChar = e.getKeyChar();
        if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

        } else if(keyChar == KeyEvent.VK_PERIOD || keyChar == KeyEvent.VK_MINUS){

        }else{
            e.consume(); //关键，屏蔽掉非法输入
        }
    }
}
