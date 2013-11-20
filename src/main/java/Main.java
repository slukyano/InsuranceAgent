import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.UiRootController;

import java.util.ArrayList;

public class Main extends Application {
    private ToolBar bc1;
    private ToolBar bc2;
    private ToolBar bc3;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootView = FXMLLoader.load(getClass().getResource("/ui/UiRoot.fxml"));
        Scene scene =  new Scene(rootView, 640, 480);
        scene.getStylesheets().add("/ui/pages/home/resources/style.css");

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Insurance");
        primaryStage.show();

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

}
