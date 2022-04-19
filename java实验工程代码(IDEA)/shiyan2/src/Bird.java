public class Bird extends Animal{
    public Bird(){
    }
    public Bird(String name){
        super(name);
    }

    public void fly(){
        System.out.println(super.getName() + "飞起来了吗？");
    }
    @Override
    public void eat() {
        System.out.println(super.getName() + "鸵鸟吃啥我不知道");
    }

    @Override
    public void play() {

    }
}