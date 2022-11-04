package webtech.Product;

public class Product {

    private Long id;
    private String name;
    private int kcal;


    public Product(Long id, String name, int kcal) {
        this.id = id;
        this.name = "Portion of "+name;
        this.kcal = kcal;
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
}
