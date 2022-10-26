package sql.demo.model;

public class Cat extends BaseModel {
    private String name;
    private int typeId;
    private int age;
    private double weight;

    public Cat(){}
    public Cat(String name){
        this.name = name;
    }
    public Cat(String name, int typeId, int age, double weight) {
        this.name = name;
        this.typeId = typeId;
        this.age = age;
        this.weight = weight;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
