package webtech.Day;
import java.util.List;

public class Day {

    private Long id;
    private String date;
    private List<Long> productsId;
    private int TodayKcal;

    public Day(Long id, String date, List<Long> productsId, int todayKcal) {
        this.id = id;
        this.date = date;
        this.productsId = productsId;
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

    public List<Long> getProductsId() {
        return productsId;
    }

    public void setProductsId(List<Long> productsId) {
        this.productsId = productsId;
    }

    public int getTodayKcal() {
        return TodayKcal;
    }

    public void setTodayKcal(int todayKcal) {
        TodayKcal = todayKcal;
    }
}
