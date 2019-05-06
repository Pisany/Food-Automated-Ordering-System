package client.Controller;

import client.DataModel.Order;
import client.Model.Client;
import client.Model.Receiver;
import client.Model.Sender;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainPaneController implements Initializable {

    //********************************************************
    //TIME LABELS
    @FXML
    public Label labelTime;
    @FXML
    public Label labelData;

    //********************************************************
    //TABS AND TABS IMAGES
    @FXML
    public Tab tabPane1;
    @FXML
    public Tab tabPane2;
    @FXML
    public Tab tabPane3;
    @FXML
    public Tab tabPane4;

    @FXML
    public ImageView tabImage1;
    @FXML
    public ImageView tabImage2;
    @FXML
    public ImageView tabImage3;
    @FXML
    public ImageView tabImage4;
    //********************************************************
    //ORDER COMPONENST
    @FXML
    public ImageView imageRemoveOrder;
    @FXML
    public ImageView imageConfirm;
    @FXML
    public ImageView cashImage;
    @FXML
    public Label totalPriceLabel;
    @FXML
    public ImageView imageOrder;
    @FXML
    public TextField textFieldOrderAmount;
    @FXML
    public ImageView imageOrderAmountAdd;
    @FXML
    public ImageView imageOrderAmountRemove;
    @FXML
    public ImageView imageOrderAdd;
    //********************************************************
    //TABLE COMPONETS
    @FXML
    public TableView<Order> tableViewOrderList;
    @FXML
    public TableColumn<Order, String> orderNameColumn;
    @FXML
    public TableColumn<Order, String> orderAmountColumn;
    @FXML
    public TableColumn<Order, String> orderPriceColumn;
    //********************************************************
    // FIRST PAGE
    @FXML
    public ImageView mealImage11;
    @FXML
    public ImageView mealImage21;
    @FXML
    public ImageView mealImage31;
    @FXML
    public ImageView mealImage41;
    @FXML
    public ImageView mealImage51;
    @FXML
    public ImageView mealImage61;

    @FXML
    public Label mealInfo11;
    @FXML
    public Label mealInfo21;
    @FXML
    public Label mealInfo31;
    @FXML
    public Label mealInfo41;
    @FXML
    public Label mealInfo51;
    @FXML
    public Label mealInfo61;
    //********************************************************
    // SECOND PAGE
    @FXML
    public ImageView mealImage12;
    @FXML
    public ImageView mealImage22;
    @FXML
    public ImageView mealImage32;
    @FXML
    public ImageView mealImage42;
    @FXML
    public ImageView mealImage52;
    @FXML
    public ImageView mealImage62;

    @FXML
    public Label mealInfo12;
    @FXML
    public Label mealInfo22;
    @FXML
    public Label mealInfo32;
    @FXML
    public Label mealInfo42;
    @FXML
    public Label mealInfo52;
    @FXML
    public Label mealInfo62;
    //********************************************************
    // THIRD PAGE
    @FXML
    public ImageView mealImage13;
    @FXML
    public ImageView mealImage23;
    @FXML
    public ImageView mealImage33;
    @FXML
    public ImageView mealImage43;
    @FXML
    public ImageView mealImage53;
    @FXML
    public ImageView mealImage63;

    @FXML
    public Label mealInfo13;
    @FXML
    public Label mealInfo23;
    @FXML
    public Label mealInfo33;
    @FXML
    public Label mealInfo43;
    @FXML
    public Label mealInfo53;
    @FXML
    public Label mealInfo63;
    //********************************************************
    // FOURTH PAGE
    @FXML
    public ImageView mealImage14;
    @FXML
    public ImageView mealImage24;
    @FXML
    public ImageView mealImage34;
    @FXML
    public ImageView mealImage44;
    @FXML
    public ImageView mealImage54;
    @FXML
    public ImageView mealImage64;

    @FXML
    public Label mealInfo14;
    @FXML
    public Label mealInfo24;
    @FXML
    public Label mealInfo34;
    @FXML
    public Label mealInfo44;
    @FXML
    public Label mealInfo54;
    @FXML
    public Label mealInfo64;
    //********************************************************

    private static final String MEGA_BURGER_PNG = "Icon/Meal/Burgers/mega-burger.png";
    private static final String FRENCH_FRIES_PNG = "Icon/Meal/Fries/french-fries.png";
    private static final String ORANGE_LEMON_PNG = "Icon/Meal/Drinks/ice-orange-lemon.png";
    private static final String CHOCOLATE_COOKIE_PNG = "Icon/Meal/Cake/chocolate-cookie.png";

    private static final String PLUS_PNG = "Icon/plus.png";
    private static final String MINUS_PNG = "Icon/minus.png";
    private static final String CASH_PNG = "Icon/cash.png";

    private static final String ADD_ORDER_PNG = "Icon/add-order.png";
    private static final String PAY_PNG = "Icon/pay.png";
    private static final String REMOVE_ORDER_PNG = "Icon/remove-order.png";

    private IntegerProperty orderAmount = new SimpleIntegerProperty(0);
    private DoubleProperty totalPrice = new SimpleDoubleProperty(0);
    private ObservableList<Order> orderList = FXCollections.observableArrayList();

    private ArrayList<ImageView> firstPageImageArray = new ArrayList<>();
    private ArrayList<ImageView> secondPageImageArray = new ArrayList<>();
    private ArrayList<ImageView> thirdPageImageArray = new ArrayList<>();
    private ArrayList<ImageView> fourthPageImageArray = new ArrayList<>();

    private ArrayList<Label> firstPageLabelArray = new ArrayList<>();
    private ArrayList<Label> secondPageLabelArray = new ArrayList<>();
    private ArrayList<Label> thirdPageLabelArray = new ArrayList<>();
    private ArrayList<Label> fourthPageLabelArray = new ArrayList<>();

    private Receiver receiver;
    private String id;

    private Sender sender;
    private int idPayment = 0;
    private int idOrder = 0;


    public void initialize(URL location, ResourceBundle resources) {

        textFieldOrderAmount.setDisable(true);
        textFieldOrderAmount.textProperty().bind(orderAmount.asString());
        totalPriceLabel.textProperty().bind(totalPrice.asString());


        tableViewOrderList.setPlaceholder(new ImageView(new Image("Icon/los-pollos-hermanos.jpg")));

        setClock();

        bindColumn();

        starCommunication();

        addImageToList();
        addLabelToList();

        setTabImage();
        setImageAndLabel();
        setOrderComponentsVisible(false);
        setFinalizeTransactionComponentsVisible(false);

        setBasicControllers();

    }

    private void setClock() {

        labelData.setText(String.valueOf(LocalDate.now()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            int second = LocalDateTime.now().getSecond();
            int minute = LocalDateTime.now().getMinute();
            int hour = LocalDateTime.now().getHour();
            labelTime.setText(hour + ":" + (minute) + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void bindColumn() {
        orderNameColumn.setCellValueFactory(new PropertyValueFactory<>("mealName"));
        orderAmountColumn.setCellValueFactory(new PropertyValueFactory<>("mealAmount"));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>("mealTotalPrice"));
    }

    private void setTabImage() {
        tabImage1.setImage(new Image(MEGA_BURGER_PNG));
        tabImage2.setImage(new Image(FRENCH_FRIES_PNG));
        tabImage3.setImage(new Image(ORANGE_LEMON_PNG));
        tabImage4.setImage(new Image(CHOCOLATE_COOKIE_PNG));
    }

    private void addImageToList() {
        firstPageImageArray.add(mealImage11);
        firstPageImageArray.add(mealImage21);
        firstPageImageArray.add(mealImage31);
        firstPageImageArray.add(mealImage41);
        firstPageImageArray.add(mealImage51);
        firstPageImageArray.add(mealImage61);

        secondPageImageArray.add(mealImage12);
        secondPageImageArray.add(mealImage22);
        secondPageImageArray.add(mealImage32);
        secondPageImageArray.add(mealImage42);
        secondPageImageArray.add(mealImage52);
        secondPageImageArray.add(mealImage62);

        thirdPageImageArray.add(mealImage13);
        thirdPageImageArray.add(mealImage23);
        thirdPageImageArray.add(mealImage33);
        thirdPageImageArray.add(mealImage43);
        thirdPageImageArray.add(mealImage53);
        thirdPageImageArray.add(mealImage63);

        fourthPageImageArray.add(mealImage14);
        fourthPageImageArray.add(mealImage24);
        fourthPageImageArray.add(mealImage34);
        fourthPageImageArray.add(mealImage44);
        fourthPageImageArray.add(mealImage54);
        fourthPageImageArray.add(mealImage64);

    }

    private void addLabelToList() {
        firstPageLabelArray.add(mealInfo11);
        firstPageLabelArray.add(mealInfo21);
        firstPageLabelArray.add(mealInfo31);
        firstPageLabelArray.add(mealInfo41);
        firstPageLabelArray.add(mealInfo51);
        firstPageLabelArray.add(mealInfo61);

        secondPageLabelArray.add(mealInfo12);
        secondPageLabelArray.add(mealInfo22);
        secondPageLabelArray.add(mealInfo32);
        secondPageLabelArray.add(mealInfo42);
        secondPageLabelArray.add(mealInfo52);
        secondPageLabelArray.add(mealInfo62);

        thirdPageLabelArray.add(mealInfo13);
        thirdPageLabelArray.add(mealInfo23);
        thirdPageLabelArray.add(mealInfo33);
        thirdPageLabelArray.add(mealInfo43);
        thirdPageLabelArray.add(mealInfo53);
        thirdPageLabelArray.add(mealInfo63);

        fourthPageLabelArray.add(mealInfo14);
        fourthPageLabelArray.add(mealInfo24);
        fourthPageLabelArray.add(mealInfo34);
        fourthPageLabelArray.add(mealInfo44);
        fourthPageLabelArray.add(mealInfo54);
        fourthPageLabelArray.add(mealInfo64);
    }

    private void setImageAndLabel() {
        //wyswietlanie komponentow z list

        for (int i = 0; i < receiver.getFirstTab().size(); i++) {
            String[] cutedString = cutString(receiver.getFirstTab().get(i));
            firstPageImageArray.get(i).setImage(new Image(cutedString[5]));
            firstPageLabelArray.get(i).setText(cutedString[2] + "\n" + cutedString[4] + "g. " + cutedString[3] + " $");
        }
        for (int i = 0; i < receiver.getSecondTab().size(); i++) {
            String[] cutedString = cutString(receiver.getSecondTab().get(i));
            secondPageImageArray.get(i).setImage(new Image(cutedString[5]));
            secondPageLabelArray.get(i).setText(cutedString[2] + "\n" + cutedString[4] + "g. " + cutedString[3] + " $");
        }
        for (int i = 0; i < receiver.getThirdTab().size(); i++) {
            String[] cutedString = cutString(receiver.getThirdTab().get(i));
            thirdPageImageArray.get(i).setImage(new Image(cutedString[5]));
            thirdPageLabelArray.get(i).setText(cutedString[2] + "\n" + cutedString[4] + "ml. " + cutedString[3] + " $");
        }
        for (int i = 0; i < receiver.getFourthTab().size(); i++) {
            String[] cutedString = cutString(receiver.getFourthTab().get(i));
            fourthPageImageArray.get(i).setImage(new Image(cutedString[5]));
            fourthPageLabelArray.get(i).setText(cutedString[2] + "\n" + cutedString[4] + "g. " + cutedString[3] + " $");
        }

    }

    private void setBasicControllers() {
        imageOrderAmountAdd.setImage(new Image(PLUS_PNG));
        imageOrderAmountRemove.setImage(new Image(MINUS_PNG));
        imageOrderAdd.setImage(new Image(ADD_ORDER_PNG));
        cashImage.setImage(new Image(CASH_PNG));
        imageConfirm.setImage(new Image(PAY_PNG));
        imageRemoveOrder.setImage(new Image(REMOVE_ORDER_PNG));

        imageOrderAmountAdd.setVisible(false);
        imageOrderAmountRemove.setVisible(false);
        imageOrderAdd.setVisible(false);

    }

    private void starCommunication() {
        sender = new Sender();
        Thread senderThread = new Thread(sender);
        senderThread.start();

        receiver = new Receiver();
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sender.getId();
        sender.getMealList();
    }

    public void setOrderImage(MouseEvent mouseEvent) {
//kopiowanie obrazka na zamowienie na dol
        id = mouseEvent.getPickResult().getIntersectedNode().getId(); //mealImage11
        id = id.replace("mealImage", ""); //11
        String[] idParts = id.split("");
        String cutedString;

        if (Integer.parseInt(idParts[1]) == 1) {
            cutedString = cutString(receiver.getFirstTab().get(Integer.parseInt(idParts[0]) - 1), 5);
            imageOrder.setImage(new Image(cutedString));

        } else if (Integer.parseInt(idParts[1]) == 2) {
            cutedString = cutString(receiver.getSecondTab().get(Integer.parseInt(idParts[0]) - 1), 5);
            imageOrder.setImage(new Image(cutedString));

        } else if (Integer.parseInt(idParts[1]) == 3) {
            cutedString = cutString(receiver.getThirdTab().get(Integer.parseInt(idParts[0]) - 1), 5);
            imageOrder.setImage(new Image(cutedString));

        } else if (Integer.parseInt(idParts[1]) == 4) {
            cutedString = cutString(receiver.getFourthTab().get(Integer.parseInt(idParts[0]) - 1), 5);
            imageOrder.setImage(new Image(cutedString));
        }


        if (imageOrder != null) {
            setOrderComponentsVisible(true);
        }
    }


    public void changeOrderAmount(MouseEvent mouseEvent) {

        //zminaa ilości danego prod
        if (mouseEvent.getPickResult().getIntersectedNode().getId().equals("imageOrderAmountAdd")) {
//            System.out.println("add");
            orderAmount.set(orderAmount.getValue() + 1);

        } else {
            if (orderAmount.getValue() > 0) {
//                System.out.println("remove");
                orderAmount.set(orderAmount.getValue() - 1);
            }
        }
    }

    public void addToOrderList() {
        //dod do tb
        String[] idParts = id.split("");


        if (orderAmount.getValue() != 0) {
            String temp;
            idOrder = idOrder + 1;


            if (Integer.parseInt(idParts[1]) == 1) {
                //FIRST PAGE
                temp = receiver.getFirstTab().get(Integer.parseInt(idParts[0]) - 1);

                fillComponent(temp, idOrder);

            } else if (Integer.parseInt(idParts[1]) == 2) {
                //SECOND PAGE
                temp = receiver.getSecondTab().get(Integer.parseInt(idParts[0]) - 1);
                fillComponent(temp, idOrder);

            } else if (Integer.parseInt(idParts[1]) == 3) {
                //THIRD PAGE
                temp = receiver.getThirdTab().get(Integer.parseInt(idParts[0]) - 1);
                fillComponent(temp, idOrder);

            } else if (Integer.parseInt(idParts[1]) == 4) {
                //FOURTH PAGE
                temp = receiver.getFourthTab().get(Integer.parseInt(idParts[0]) - 1);
                fillComponent(temp, idOrder);
            }
            tableViewOrderList.setItems(orderList);
            cleanOrderComponents();
        }
    }

    private void fillComponent(String temp, int idOrder) {

        //dod do listy
        String[] parts;
        parts = temp.split("-#-");
        Order order = new Order(idOrder, parts[2], Integer.parseInt(parts[1]), orderAmount.getValue(), Double.parseDouble(parts[3]), Double.parseDouble(parts[3]) * orderAmount.getValue());
        orderList.add(order);
        totalPrice.setValue(totalPrice.getValue() + order.getMealTotalPrice());
        setFinalizeTransactionComponentsVisible(true);

    }

    private String cutString(String string, int index) {
        String parts[];
        parts = string.split("-#-");
        return parts[index];
    }

    private String[] cutString(String string) {
        String parts[];
        parts = string.split("-#-");
        return parts;
    }

    private void cleanOrderComponents() {

        //czyszcenie zam
        imageOrder.setImage(null);

        imageOrderAmountAdd.setVisible(false);
        imageOrderAmountRemove.setVisible(false);
        imageOrderAdd.setVisible(false);

        orderAmount.set(0);
        textFieldOrderAmount.setVisible(false);

    }

    private void setOrderComponentsVisible(Boolean flag) {

        //ustawienie widzialności
        imageOrderAmountAdd.setVisible(flag);
        imageOrderAmountRemove.setVisible(flag);
        imageOrderAdd.setVisible(flag);
        textFieldOrderAmount.setVisible(flag);
    }

    private void setFinalizeTransactionComponentsVisible(Boolean flag) {

        //podsumowanie
        cashImage.setVisible(flag);
        imageRemoveOrder.setVisible(flag);
        imageConfirm.setVisible(flag);
//        cashImage.setVisible(flag);
        totalPriceLabel.setVisible(flag);
    }

    public void confimrTransaction() {
        idPayment = idPayment + 1;
        idOrder = 0;
        prepareRecipe();
        tableViewOrderList.getItems().removeAll(orderList);
        totalPrice.setValue(0);
        cleanOrderComponents();
        setOrderComponentsVisible(false);
        setFinalizeTransactionComponentsVisible(false);


    }

    public void removeSelectedOrder() {
        if (!tableViewOrderList.getSelectionModel().isEmpty()) {
            totalPrice.setValue(totalPrice.getValue() - (tableViewOrderList.getSelectionModel().getSelectedItem().getMealPrice() * tableViewOrderList.getSelectionModel().getSelectedItem().getMealAmount()));
            tableViewOrderList.getItems().removeAll(tableViewOrderList.getSelectionModel().getSelectedItem());
        }
    }

    private void prepareRecipe() {
        ArrayList<String> recipeArray = new ArrayList<String>();
        recipeArray.add("ID: " + Client.getID() + "\n");
        recipeArray.add(labelTime.getText() + "  ");
        recipeArray.add(labelData.getText() + "\n");

        String recipe = Client.getID() + " - " + labelData.getText() + " - " + idPayment;

        for (Order anOrderList : orderList) {
            recipeArray.add(anOrderList.getIdOrder() + ":  " + anOrderList.getIdProduktu() + "--" + anOrderList.getMealName() + " ");
            recipeArray.add(String.valueOf(anOrderList.getMealAmount()));
            recipeArray.add(" x ");
            recipeArray.add(String.valueOf(anOrderList.getMealPrice()));
            recipeArray.add(" = ");
            recipeArray.add(anOrderList.getMealTotalPrice() + "\n");

            recipe = recipe.concat("idpro - " + anOrderList.getIdProduktu() + "ilosc - " + anOrderList.getMealAmount() + "cena - " + anOrderList.getMealTotalPrice());
        }
        recipeArray.add("Suma: " + totalPrice.getValue() + ("\n"));
        recipeArray.add("Platnosc: " + idPayment);


        System.out.println(recipeArray);
        System.out.println("\n" + recipe);
        sender.setRecipe(recipe);
        sender.sendOrder();

    }
}