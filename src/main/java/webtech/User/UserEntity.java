package webtech.User;


import javax.persistence.*;


@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @Column(name = "USER_PASSWORT", nullable = false)
    private String passwort;

    @Column(name = "USER_WEIGHT", nullable = false)
    private double weight;

    @Column(name = "USER_HEIGHT", nullable = false)
    private double height;

    @Column(name = "USER_AGE")
    private int age;

    @Column(name = "USER_BMI", nullable = false)
    private double bmi;

    @Column(name = "USER_CATEGORY")
    private String category;

    @Column(name = "GOAL_WEIGHT", nullable = false)
    private int goalW;
    @Column(name = "USER_BMR", nullable = false)
    private int bmr;


    public UserEntity(String username, String passwort, double weight,double height, int age, double bmi,String category, int goalW, int bmr) {
        this.username = username;
        this.passwort = passwort;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmi = bmi;
        this.category = category;
        this.goalW = goalW;
        this.bmr = bmr;
    }

    protected UserEntity(){

    }

    public long getId() {
        return id;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public int getGoalW() {
        return goalW;
    }

    public void setGoalW(int goalW) {
        this.goalW = goalW;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
