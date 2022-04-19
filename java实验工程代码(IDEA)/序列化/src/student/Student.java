package student;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private transient String card;
    private Double java;
    private Double sql;
    private Double jsp;

    public Student() {
    }

    public Student(String id, String name, String card, Double java, Double sql, Double jsp) {
        this.id = id;
        this.name = name;
        this.card = card;
        this.java = java;
        this.sql = sql;
        this.jsp = jsp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Double getJava() {
        return java;
    }

    public void setJava(Double java) {
        this.java = java;
    }

    public Double getSql() {
        return sql;
    }

    public void setSql(Double sql) {
        this.sql = sql;
    }

    public Double getJsp() {
        return jsp;
    }

    public void setJsp(Double jsp) {
        this.jsp = jsp;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", java=" + java +
                ", sql=" + sql +
                ", jsp=" + jsp +
                '}';
    }

    public Double sum() {
        return java + sql + jsp;
    }
}
