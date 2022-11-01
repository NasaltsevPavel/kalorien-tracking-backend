package webtech.User;


public class User {

    private long id;
    private String username;
    private String passwort;
    private double weight;
    private double height;
    private int age;
    private double bmi;
    private String category;
    private int goalW;
    private int bmr;


    public User(long id, String username, String passwort,
                double weight, double height,
                int age, int goalW) {
        this.id = id;
        this.username = username;
        this.passwort = passwort;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bmi = calcBmi();
        this.category = calcRes();
        this.goalW = goalW;
        this.bmr = calcBmr();

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

        if (this.bmi < 16) {

            category = "Severe Thinness";

        }

        if (this.bmi > 16 && this.bmi < 17) {

            category = "Moderate Thinnes";

        }
        if (this.bmi > 17 && this.bmi < 18.5) {

            category = "Mild Thinness";

        }

        if (this.bmi > 18.5 && this.bmi < 25) {

            category = "Normal";

        }

        if (this.bmi > 25 && this.bmi < 30) {

            category = "Overweight";

        }

        if (this.bmi > 30 && this.bmi < 35) {

            category = "Obese Class I";

        }

        if (this.bmi > 35 && this.bmi < 40) {

            category = "Obese Class II";

        }

        if (this.bmi > 40) {

            category = "Obese Class III";

        }

        return category;


    }

    public double calcBmi() {

        bmi = weight /((height/100)*(height/100));

        return bmi;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public String getPassword() {
        return passwort;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
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

    public String getPasswort() {
        return passwort;
    }


}
