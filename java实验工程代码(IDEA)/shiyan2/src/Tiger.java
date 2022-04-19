public class Tiger extends Mammalia{

    /**耳朵颜色*/
    private String earColor;


    public Tiger(){
    }

    public Tiger(String name) {
        super(name);
    }

    public String getEarColor() {
        return earColor;
    }

    public void setEarColor(String earColor) {
        this.earColor = earColor;
    }

    @Override
    public void eat() {
        System.out.println(super.getName() + "正在吃肉");
    }

    @Override
    public void talk() {
        System.out.println(super.getName() + "正在和老虎交流");
    }

    /**狩猎*/
    @Override
    public void play() {
        System.out.println(super.getName() + "正在狩猎");
    }

}
