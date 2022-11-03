package webtech.Day;
import java.util.List;

public class Day {

    private Long id;
    private String date;
    private List<String> productsNames;
    private int TodayKcal;

    public Day(Long id, String date,List<String> productsNames, int todayKcal) {
        this.id = id;
        this.date = date;
        this.productsNames = productsNames;
        TodayKcal = todayKcal;
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
