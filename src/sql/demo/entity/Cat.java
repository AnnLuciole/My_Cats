package sql.demo.entity;

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

    public Cat(String name, int age, double weight) {
        this.name = name;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return (super.getId() == cat.getId() &&
                (this.name == cat.getName() ||
                (this.name != null &&
                cat.getName() != null &&
                this.name.equals(cat.getName()))) &&
                this.age == cat.getAge() &&
                this.weight == cat.getWeight());
    }

    @Override
    public String toString() {
        return "Name: " + name + ". Type ID: " + typeId + ". Age: " + age + ". Weight: " + weight + ".";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + super.getId();
        result = prime * result + age;
        result = prime * result + (int) weight;
        result = prime * result + typeId;
        return result;
    }
}