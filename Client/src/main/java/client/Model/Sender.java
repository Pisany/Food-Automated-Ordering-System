package client.Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static client.Model.Client.IP;
import static client.Model.Client.PORT_S;

//Sender class is responsible for send messages to server
public class Sender implements Runnable {

    private PrintWriter printWriter;
    private static Socket socket;
    private String recipe;


    @Override
    public void run() {
        System.out.println("StartSender");
        try {
            socket = new Socket(IP, PORT_S);

            System.out.println("Sender połączony z " + socket);

            printWriter = new PrintWriter(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getId() {
        printWriter.println("#getID#");
        printWriter.flush();
    }

    public void getMealList() {
        printWriter.println("#getMealList#");
        printWriter.flush();
    }

    public void sendOrder() {
        recipe=recipe.replace("\n","--");
        printWriter.println("#recipe#  " + recipe );
        printWriter.flush();

    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    static Socket getSocket() {
        return socket;
    }
}

