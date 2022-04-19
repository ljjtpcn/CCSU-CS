public class Penguin extends Bird{
    public Penguin(){
    }
    public Penguin(String name){
        super(name);
    }

    @Override
    public void fly(){
        System.out.println(super.getName() + "飞不起来");
    }
    @Override
    public void eat() {
        System.out.println(super.getName() + "企鹅吃虾米");
    }

    @Override
    public void play() {
        System.out.println("企鹅摇摆走路");
    }
}
