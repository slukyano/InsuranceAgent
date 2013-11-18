package ui.login;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.ModelController;
import ui.UiRootController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable, ChangeListener<String> {
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField connectionField;
    public Text infoText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usernameField.textProperty().addListener(this);
        passwordField.textProperty().addListener(this);
        connectionField.textProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
        // clear info when credentials change
        infoText.setText("");
    }

    public void loginClick(ActionEvent actionEvent) {
        try {
            // will throw exception if fail to log in
            ModelController.initializeInstance(connectionField.getText(),
                    usernameField.getText(),
                    passwordField.getText());
        }
        catch (SQLException e) {
            e.printStackTrace();
            infoText.setText("Error while connecting to database");
            infoText.setFill(Color.RED);
        }

        UiRootController.getInstance().PresentHomeView();
    }
}
