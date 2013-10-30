import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelController;

import java.sql.SQLException;
import java.util.Locale;

public class Main extends Application {

    public static void main(String[] args) {
        //launch(args);

        ModelController.initializeInstance("jdbc:oracle:thin:@5.19.237.145:65432:xe", "system", "1234");
        try {
            Locale.setDefault(Locale.ENGLISH);
            System.out.println(ModelController.getInstance().getNaturalPerson(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
