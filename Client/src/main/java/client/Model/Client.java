package client.Model;

import client.utils.FxmlUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




public class Client extends Application {

    static final int PORT_S = 5000; // wysyłanie
    static final int PORT_R = 5001; // odbieranie
    static final String IP = "127.0.0.1";

    private static String ID;

//    static final int PING_DEALY = 10000; //ms

    private static final String MAIN_STAGE_FXML = "/View/MainPane.fxml";

    //start
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = FxmlUtils.fxmlLoader(MAIN_STAGE_FXML);
        assert root != null : "Root - FxmlUtils is NULL";
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);


        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        primaryStage.setTitle("Food-Automated-Ordering-System");

    }


    @Override
    public void stop() throws Exception {
        Sender.getSocket().close();
        Receiver.getSocket().close();
    }

    public static String getID() {
        return ID;
    }

    static void setID(String ID) {
        Client.ID = ID;
        System.out.println(ID);
    }
}
