import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

        ModelController.initializeInstance("","","");
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
