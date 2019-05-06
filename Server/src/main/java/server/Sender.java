package server;

import server.dbutils.Driver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender implements Runnable {

    private PrintWriter printWriter;
    private Socket socket;
    private Driver driver;




    Sender(Socket socket, Driver driver) {
        this.socket = socket;
        this.driver = driver;
    }

    void sendID() {
        printWriter.println("#ID# " + Server.getLastID());
        printWriter.flush();
        Server.setLastID(Server.getLastID()+1);
    }

    void sendMealList() {
        printWriter.println("#mealList# "+driver.getFoo());
        printWriter.flush();
    }


    @Override
    public void run() {
        try {
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
