package webtech.User;


import webtech.Product.Product;
import webtech.Product.ProductEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


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

    @Column(name = "USER_GENDER")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;




    public UserEntity(String username, String passwort,
                      double weight,double height, int age, double bmi,String category,
                      int goalW, int bmr, Gender gender ) {
        this.username = username;
        this.passwort = passwort;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmi = calcBmi();
        this.category = calcRes();
        this.goalW = goalW;
        this.bmr = calcBmr();
        this.gender= gender;


    }




    protected UserEntity(){

    }

    private int calcBmr() {

        bmr += (66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age));

        if (goalW>weight){
            bmr = bmr + 500;
        }

        if ((goalW<weight)){
            bmr = bmr - 500;
        }

        return bmr;


    }

    private String calcRes() {

        if (this.bmi <= 16) {

            category = "Severe Thinness";

        }

        if (this.bmi > 16 && this.bmi <= 17) {

            category = "Moderate Thinnes";

        }
        if (this.bmi > 17 && this.bmi <= 18.5) {

            category = "Mild Thinness";

        }

        if (this.bmi > 18.5 && this.bmi <= 25) {

            category = "Normal";

        }

        if (this.bmi > 25 && this.bmi <= 30) {

            category = "Overweight";

        }

        if (this.bmi > 30 && this.bmi <= 35) {

            category = "Obese Class I";

        }

        if (this.bmi > 35 && this.bmi <= 40) {

            category = "Obese Class II";

        }

        if (this.bmi > 40) {

            category = "Obese Class III";

        }

        return category;


    }

    public double calcBmi() {

        bmi = weight /((height/100)*(height/100));

        bmi = Math.round(bmi);

        return bmi;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
