package client.Model;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;

public class ClientTest extends ApplicationTest {



    @Before
    public void setUp() throws Exception {
        ApplicationTest.launch(Client.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
    }

    @After
    public void tearDown() throws Exception {
//        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void addToOrder() throws InterruptedException {
        clickOn("#mealImage11");

        for(int i =0; i <4;i++) {
            clickOn("#imageOrderAmountAdd");
        }
        clickOn("#imageOrderAdd");
        assertThat(lookup("#totalPriceLabel").queryAs(Label.class)).hasText("16.0");

        clickOn("#mealImage11");

//        clickOn("#tabPane2"); 28



    }
}