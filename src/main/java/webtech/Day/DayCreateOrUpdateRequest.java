package webtech.Day;
import java.util.ArrayList;
import java.util.List;

public class DayCreateOrUpdateRequest {


    private Long id;
    private int day;
    private int month;
    private int year;
    private List<String> productsNames = new ArrayList<>();
    private int TodayKcal ;
    private Long userId = 1L;
    private String season;

    public DayCreateOrUpdateRequest(int day, int month, int year) {

        this.day = day;
        this.month = month;
        this.year = year;


    }

    public DayCreateOrUpdateRequest() {}

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
