public class AllDemo {
    public static void main(String[] args) {

        Animal[] animals = {
                new Tiger("老虎1号"),new Tiger("老虎2号"), new Tiger("老虎3号"),
                new Panda("熊猫1号"), new Panda("熊猫2号"),
                new Hippo("河马1号"),
                new Boa("蟒蛇1号"),
                new Crocodile("鳄鱼1号"), new Crocodile("鳄鱼2号"),
                new Penguin("企鹅1号"),
                new Eagle("老鹰1号"), new Eagle("老鹰2号"),
                new Ostrich("鸵鸟1号"), new Ostrich("鸵鸟2号")
        };

        for (Animal user: animals){
            user.eat();
            user.play();
        }
    }   
}
