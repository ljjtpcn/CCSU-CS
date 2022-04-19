public class Panda extends Mammalia{
    public Panda(){
    }

    public Panda(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + "正在吃竹子");
    }

    @Override
    public void talk() {
        System.out.println(super.getName() + "正在和熊猫交流");
    }

    /**睡觉*/
    @Override
    public void play() {
        System.out.println(super.getName() + "正在睡大觉");
    }
}
