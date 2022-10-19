package sql.demo.model;

public class CatType extends BaseModel {
    private String type;

    public CatType(){}

    public CatType(int id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
