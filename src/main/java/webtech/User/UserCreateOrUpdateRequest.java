package webtech.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UserCreateOrUpdateRequest {


    private String username;
    @NotNull(message = "The weight number must not be null")
    @Positive(message = "The weight number can't be negativ")
    private int weight;
    @NotNull(message = "The height number must not be null")
    @Positive(message = "The height number can't be negativ")
    private int height;
    @NotNull(message = "The age number must not be null")
    @Positive(message = "The age number can't be negativ")
    private int age;
    private double bmi;
    private String category;
    @NotNull(message = "The goal weight number must not be null")
    @Positive(message = "The goal weight number can't be negativ")
    private int goalW;
    private int bmr;
    private String gender;




    public UserCreateOrUpdateRequest(String username, int weight, int height, int age, double bmi,
                                     String category, int goalW
            , int bmr, String gender) {
        this.username = username;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmi = bmi;
        this.category = category;
        this.goalW = goalW;
        this.bmr = bmr;
        this.gender=gender;


    }

    public UserCreateOrUpdateRequest() {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCategory() {
        return category;
    }

    public int getBmr() {
        return bmr;
    }

    public void setBmr(int bmr) {
        this.bmr = bmr;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
