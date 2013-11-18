import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UiRootController;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/ui/UiRoot.fxml"));

        primaryStage.setScene(new Scene(loginRoot, 640, 480));
        primaryStage.sizeToScene();
        primaryStage.setTitle("Insurance");
        primaryStage.show();

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

}
