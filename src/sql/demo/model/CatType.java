package sql.demo.model;

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
}
