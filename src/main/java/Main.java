import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

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
        scene.getStylesheets().add("/ui/resources/styles/style.css");
        scene.getStylesheets().add("/ui/resources/styles/DarkTheme.css");
        scene.getStylesheets().add("/ui/resources/styles/DatePicker.css");

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Insurance");
        primaryStage.show();

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());
    }

}
