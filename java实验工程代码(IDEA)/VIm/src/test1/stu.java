package test1;

public class stu {
    private int age;
    public void setting(int age) {
        if(age<=200&&age>=0) {
            this.age=age;
        }else {
            System.out.println("错误");
        }
    }
    public int getting() {
        return age;
    }
}
