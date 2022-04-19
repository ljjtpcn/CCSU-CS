package cn.test;

import cn.day01.Calculator;
import org.junit.jupiter.api.Test;

/**
 * @author 李佳骏 ljjtpcn@163.com
 * @date [2021/11/22 13:12]
 * @description TODO 此为Calculator单元测试类
 */
public class CalculatorTest {

    /**
     * @description TODO 单元测试 红色失败 绿色成功
     * @param []
     * @return [void]
     * @date [2021/11/22 13:12]
     */
    @Test
    public void testAdd() {
        Calculator c = new Calculator();
        //调用add方法
        //System.out.println(c.add(1, 2));

        //断言(期望值, 真实值) 我断言 1+2 = 3
    }

}
