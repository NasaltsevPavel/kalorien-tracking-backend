package webtech.Product;


import webtech.Day.DayEntity;
import webtech.Day.DaySeason;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false)
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_KCAL", nullable = false)
    private int kcal;

    @ManyToMany(mappedBy = "products")
    private List<DayEntity> dayEntityList = new ArrayList<>();

    @Column(name = "PRODUCT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    public ProductEntity(String name, int kcal, ProductType type) {
        this.name = name;
        this.kcal = kcal;
        this.type=type;
    }

    protected ProductEntity() {

    }

    public Long getId() {
        return id;
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

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<DayEntity> getUserEntityList() {
        return dayEntityList;
    }

    public void setUserEntityList(List<DayEntity> userEntityList) {
        this.dayEntityList = userEntityList;
    }
}
