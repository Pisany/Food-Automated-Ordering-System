package server;



import server.dbutils.Driver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

//    private HashSet<String> hset;
//    private ArrayList clientArrayList;


    private static final int PORT_S = 5001;
    private static final int PORT_R = 5000;

    private static int lastID;

    public static void main(String[] args) {

        Server s = new Server();
        s.startSerwer();



    }




    private void startSerwer() {
//        clientArrayList = new ArrayList();
//        hset = new HashSet<>();
        System.out.println("Uruchomiono serwer.");
        Driver driver = new Driver();
        driver.getMealList();
        lastID =1;


        try {
            ServerSocket sendServerSocket = new ServerSocket(PORT_S);
            ServerSocket receiveServerSocket = new ServerSocket(PORT_R);

            while (true) {
                //Wszystkie połączenia przychdządzące na porcie 5000 będą akceptowane
                Socket receiveSocket = receiveServerSocket.accept();
                System.out.println("Słucham: " + receiveServerSocket);

                Socket sendSocket = sendServerSocket.accept();
                System.out.println("Wysyłam: " + sendServerSocket);

                Sender sender = new Sender(sendSocket, driver);
                Receiver receiver = new Receiver(receiveSocket, sender, driver);

                Thread receiverThread = new Thread(receiver);
                receiverThread.start();

                Thread senderThread = new Thread(sender);
                senderThread.start();




            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    static int getLastID() {
        return lastID;
    }

    static void setLastID(int lastID) {
        Server.lastID = lastID;
    }


}
