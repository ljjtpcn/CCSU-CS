package task3;

public class Teacher {
    public void test(int score) throws TestException {
        if (score < 0 || score > 100) {
            throw new TestException("输入分数有误, 分数应≥0且≤100");
        } else {
            System.out.println("分数正常");
        }

    }
}