package webtech.Day;
import webtech.Product.ProductEntity;
import webtech.User.User;

import java.util.List;

public class Day {

    private Long id;
    private String Date;
    private List<String> productsNames;
    private int TodayKcal;
    private User user;
    private String season;

    public Day(Long id, String Date, List<String> productsNames, int todayKcal, User user, String season) {
        this.id = id;
        this.Date = Date;
        this.productsNames = productsNames;
        this.TodayKcal = todayKcal;
        this.user = user;
        this.season=season;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
