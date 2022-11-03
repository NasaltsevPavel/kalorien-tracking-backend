package webtech.Product;


import webtech.Day.DayEntity;

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
    private List<DayEntity> userEntityList = new ArrayList<>();

    public ProductEntity(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
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

    public List<DayEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<DayEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }
}
