public class Reptilia extends Animal{

    public Reptilia(String name) {
        super(name);
    }
    public Reptilia(){
    }

    /**爬*/
    public void climb(){
        System.out.println(super.getName() + "在爬");
    }

    @Override
    public void play() {
    }
}
