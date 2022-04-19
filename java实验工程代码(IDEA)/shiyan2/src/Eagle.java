public class Eagle extends Bird{
    public Eagle(){
    }
    public Eagle(String name){
        super(name);
    }

    @Override
    public void fly(){
        System.out.println(super.getName() + "飞得很高");
    }
    @Override
    public void eat() {
        System.out.println(super.getName() + "老鹰吃小鸟");
    }

    @Override
    public void play() {
        System.out.println(super.getName() + "老鹰抓小鸡");
    }
}
