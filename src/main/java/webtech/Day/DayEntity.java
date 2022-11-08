package webtech.Day;



import webtech.Product.ProductEntity;
import javax.persistence.*;
import java.util.List;


@Entity
public class DayEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "DAY_ID", nullable = false)
    private Long id;

    @Column(name = "DAY_DATE", nullable = false)
    private String date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "DAY_PRODUCT",
            joinColumns = @JoinColumn(name = "DAY_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<ProductEntity> products;

    @Column(name = "DAY_KCAL", nullable = false)
    private int TodayKcal;

    public DayEntity(String day, String month, String year) {
        this.date = day +"-"+month+"-"+year;
        TodayKcal = calcK();
    }

    protected DayEntity() {

    }

    private int calcK() {

        int kcalFromProd = 0;

        if (products==null){


            return 0;
        }

        else {


            for (ProductEntity pr : products) {
                kcalFromProd = kcalFromProd + pr.getKcal();
            }

            return kcalFromProd;

        }

    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void addProduct(ProductEntity productEntity){
        products.add(productEntity);
    }

    public void deleteProduct(ProductEntity productEntity){

        ProductEntity toRemove = null;

        for (ProductEntity pr : products) {

            if (pr.getName().equals(productEntity.getName())){

                toRemove = pr;
            }
        }

        products.remove(toRemove);
    }

    public int getTodayKcal() {
        return TodayKcal;
    }

    public void setTodayKcal(int todayKcal) {
        TodayKcal = todayKcal;
    }
}
