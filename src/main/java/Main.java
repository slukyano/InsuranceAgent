import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Class c = getClass();
        URL url = c.getResource("/ui/login/LoginPage.fxml");
        Parent root = FXMLLoader.load(url);

        //Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Insurance");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
}