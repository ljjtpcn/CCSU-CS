public class Hippo extends Mammalia{
    public Hippo(){
    }

    public Hippo(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + "正在喝水");
    }

    @Override
    public void talk() {
        System.out.println(super.getName() + "正在和河马交流");
    }

    /**睡觉*/
    @Override
    public void play() {
        System.out.println(super.getName() + "河马鲜生");
    }
}
