public class Crocodile extends Reptilia{

    public Crocodile(){
    }
    public Crocodile(String name){
        super(name);
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + "吃鱼");
    }

    @Override
    public void play() {
        System.out.println(super.getName() + "真皮钱包");
    }
}
