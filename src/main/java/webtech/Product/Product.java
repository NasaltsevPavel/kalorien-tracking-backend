package webtech.Product;

public class Product {

    private Long id;
    private String name;
    private int kcal;
    private String type;


    public Product(Long id, String name, int kcal, String type) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
