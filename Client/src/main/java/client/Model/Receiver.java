package client.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import static client.Model.Client.IP;
import static client.Model.Client.PORT_R;


public class Receiver implements Runnable {

    private static Socket socket;

    private BufferedReader bufferedReader;

    private ArrayList<String> firstTab = new ArrayList<>();
    private ArrayList<String> secondTab = new ArrayList<>();
    private ArrayList<String> thirdTab = new ArrayList<>();
    private ArrayList<String> fourthTab = new ArrayList<>();


    public Receiver() {
        try {
            socket = new Socket(IP, PORT_R);

            System.out.println("Receiver połączony z " + socket);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;

        try {
            while ((message = bufferedReader.readLine()) != null) {

                if (message.contains("#mealList#")) {
                    mealListChanger(message);

                }else if(message.contains("#ID# ")){
                    setDevicesId(message);
                    System.out.println("Otrzymano ID: "+ Client.getID());
                }
            }
        } catch (Exception e) {
            System.out.println("Połączenie zakończone.");
        }


    }

    private void mealListChanger(String message) {
        String[] mealsRow;
        message = message.replace("#mealList# ", "");
        mealsRow = message.split("--");// -- end of meal

        for (String aMealsRow : mealsRow) {
            if (aMealsRow.startsWith("1")) {
                firstTab.add(aMealsRow);
            } else if (aMealsRow.startsWith("2")) {
                secondTab.add(aMealsRow);
            } else if (aMealsRow.startsWith("3")) {
                thirdTab.add(aMealsRow);
            } else if (aMealsRow.startsWith("4")) {
                fourthTab.add(aMealsRow);
            }
        }
    }

    private void setDevicesId(String message){
        message = message.replace("#ID# ","");
        Client.setID(message);
    }

    static Socket getSocket() {
        return socket;
    }

    public ArrayList<String> getFirstTab() {
        return firstTab;
    }

    public ArrayList<String> getSecondTab() {
        return secondTab;
    }

    public ArrayList<String> getThirdTab() {
        return thirdTab;
    }

    public ArrayList<String> getFourthTab() {
        return fourthTab;
    }
}
