package day2;

public class StudetDemo {
    public static void main(String[] args) {

        //lambda
        useStudentInter((name, age) -> new Student(name, age));
        //引用构造器  类名::new
        useStudentInter(Student::new);

    }

    public static void useStudentInter(StudentInter s) {
        Student student = s.build("张三", 99);
        System.out.println(student.name + student.age);
    }
}
