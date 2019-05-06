package client.DataModel;

//Data model in TableView
public class Order {

    private int idOrder;
    private String mealName;
    private int idProduktu;
    private int mealAmount;
    private Double mealPrice;
    private Double mealTotalPrice;

    public Order(int idOrder, String mealName,int idProduktu, int mealAmount, Double mealPrice, Double mealTotalPrice) {
        this.idOrder = idOrder;
        this.mealName = mealName;
        this.idProduktu = idProduktu;
        this.mealAmount = mealAmount;
        this.mealPrice = mealPrice;
        this.mealTotalPrice = mealTotalPrice;
    }

    public String getMealName() {
        return mealName;
    }

    public int getMealAmount() {
        return mealAmount;
    }

    public Double getMealPrice() {
        return mealPrice;
    }

    public Double getMealTotalPrice() {
        return mealTotalPrice;
    }


    public int getIdOrder() {
        return idOrder;
    }

    public int getIdProduktu() {
        return idProduktu;
    }
}
