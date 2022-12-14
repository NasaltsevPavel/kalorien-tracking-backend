package webtech.Day;
import java.util.List;

public class Day {

    private Long id;
    private String Date;
    private List<String> productsNames;
    private int TodayKcal;
    private Long userId;
    private String season;
    private int DayBmr;

    public Day(Long id, String Date, List<String> productsNames, int todayKcal, Long userId, String season, int dayBmr) {
        this.id = id;
        this.Date = Date;
        this.productsNames = productsNames;
        this.TodayKcal = todayKcal;
        this.userId = userId;
        this.season=season;
        this.DayBmr = dayBmr;
    }


    public Long getId() {
        return id;
    }

    public int getDayBmr() {
        return DayBmr;
    }

    public void setDayBmr(int dayBmr) {
        DayBmr = dayBmr;
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
