package webtech.Product;

public class ProductCreateOrUpdateRequest {

    private Long id;
    private String name;
    private int kcal;
    private String type;

    public ProductCreateOrUpdateRequest(String name, int kcal, String type) {
        this.name = name;
        this.kcal = kcal;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
