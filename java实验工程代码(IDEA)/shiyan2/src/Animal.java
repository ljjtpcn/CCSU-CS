/**
 * @author ljj
 */
public class Animal {
    /**名字*/
    private String name;
    /**体重*/
    private int weight;
    /**体长*/
    private int length;
    /**物种数量*/
    private int number;
    /**性别 1为雌性， 2为雄性*/
    private int sex;




    /**
     * 以下是构造方法
     */
    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    public Animal(String name, int weight, int length, int number, int sex) {
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.number = number;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void eat() {
        System.out.println(this.name + "正在吃东西");
    }

    public void sleep(){
        System.out.println(this.name + "正在睡觉");
    }

    public void move(){
        System.out.println(this.name + "正在移动");
    }

    public void play(){};

}
