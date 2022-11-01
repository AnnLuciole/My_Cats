package sql.demo.entity;

public class CatType extends BaseModel {
    private String type;

    public CatType(){}

    public CatType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatType catType = (CatType) o;
        return (this.getId() == catType.getId() &&
                (this.getType() == catType.getType() ||
                (this.getType() != null &&
                catType.getType() != null &&
                this.getType().equals(catType.getType()))));
    }

    @Override
    public String toString() {
        return "Id: " + this.getId() + ". Type: " + this.getType() + ".";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getType() == null) ? 0 : this.getType().hashCode());
        result = prime * result + super.getId();
        return result;
    }
}
