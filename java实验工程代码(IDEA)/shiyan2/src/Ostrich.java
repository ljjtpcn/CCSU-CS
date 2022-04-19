public class Ostrich extends Bird{

    public Ostrich(){
    }
    public Ostrich(String name){
        super(name);
    }

    @Override
    public void fly(){
        System.out.println(super.getName() + "飞不起来");
    }
    @Override
    public void eat() {
        System.out.println(super.getName() + "鸵鸟吃啥我不知道");
    }

    @Override
    public void play() {
        System.out.println(super.getName() + "鸵鸟下蛋");
    }
}
