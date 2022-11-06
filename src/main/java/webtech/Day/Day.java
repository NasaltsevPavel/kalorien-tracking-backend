package webtech.Day;
import webtech.Product.ProductEntity;

import java.util.List;

public class Day {

    private Long id;
    private String Date;
    private List<String> productsNames;
    private int TodayKcal;

    public Day(Long id, String Date, List<String> productsNames, int todayKcal) {
        this.id = id;
        this.Date = Date;
        this.productsNames = productsNames;
        this.TodayKcal = todayKcal;
    }


    public Long getId() {
        return id;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public List<String> getProductsNames() {
        return productsNames;
    }

    public void setProductsNames(List<String> productsNames) {
        this.productsNames = productsNames;
    }

    public int getTodayKcal() {
        return TodayKcal;
    }

    public void setTodayKcal(int todayKcal) {
        TodayKcal = todayKcal;
    }
}
