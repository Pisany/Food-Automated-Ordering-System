package client.DataModel;
//TODO do usunięcia
import java.util.ArrayList;

public class Meal {

    public static ArrayList<Meal> mealList = new ArrayList<>();

    private String image;
    private String mealName;
    private Double mealPrice;
    private String mealFilePath;

    public Meal(String image, String mealName, Double mealPrice, String mealFilePath) {
        this.image = image;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.mealFilePath = mealFilePath;
        mealList.add(this);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Double mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealFilePath() {
        return mealFilePath;
    }

    public void setMealFilePath(String mealFilePath) {
        this.mealFilePath = mealFilePath;
    }
}
