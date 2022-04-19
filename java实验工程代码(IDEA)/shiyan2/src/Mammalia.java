public class Mammalia extends Animal {

    public Mammalia(String name) {
        super(name);
    }
    public Mammalia(){

    }

    public void lactate() {
        if (this.getSex() == 1) {
            System.out.println(super.getName() + "正在分泌乳汁");
        } else {
            System.out.println(super.getName() + "您是雄性, 不能分泌乳汁");
        }
    }

    public void talk() {
        System.out.println(super.getName() + "正在和同类交流");
    }

    @Override
    public void play() {

    }
}
