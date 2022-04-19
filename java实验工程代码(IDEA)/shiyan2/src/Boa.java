public class Boa extends Reptilia {

    public Boa() {
    }

    public Boa(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + "在吞象");
    }

    @Override
    public void play() {
        System.out.println(super.getName() + "在玩贪吃蛇");
    }

}
