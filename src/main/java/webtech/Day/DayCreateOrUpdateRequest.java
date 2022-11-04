package webtech.Day;

import java.util.ArrayList;
import java.util.List;

public class DayCreateOrUpdateRequest {


    private Long id;
    private String day;
    private String month;
    private String year;
    private List<String> productsNames = new ArrayList<>();
    private int TodayKcal ;

    public DayCreateOrUpdateRequest(String day, String month, String year) {

        this.day = day;
        this.month = month;
        this.year = year;

    }


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
