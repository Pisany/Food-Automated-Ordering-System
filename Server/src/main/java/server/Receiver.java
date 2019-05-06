package server;

import server.dbutils.Driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private Sender sender;
    private Driver driver;
//    Ping ping;

    public Receiver(Socket socketClient, Sender sender, Driver driver) {
        this.sender = sender;
        this.driver = driver;

        try {
            socket = socketClient;
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
                System.out.println("Otrzymano wiadomość :" + message);

                if (message.contains("#getID#")) {
                    sender.sendID();

                } else if (message.contains("#getMealList#")) {
                    sender.sendMealList();

                } else if (message.contains("#recipe#")){
                    message=message.replace("#recipe#  ","");
                    System.out.println(message);
                    message=message.replaceAll("[a-z]", "");
                    System.out.println(message);
                    driver.saveRecipe(message);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String cutMessage(String message, String cut){

        return message;
    }
}

