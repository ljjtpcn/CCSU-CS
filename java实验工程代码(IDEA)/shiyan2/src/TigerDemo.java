public class TigerDemo {
    public static void main(String[] args) {
        Animal[] animals = {new Tiger("奇奇"),
                new Tiger("力力"), new Tiger("亮亮")};
        for(Animal animal: animals) {
            animal.eat();
        }
    }
}
