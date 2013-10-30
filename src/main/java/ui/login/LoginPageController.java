package ui.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.ModelController;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 30.10.13
 */
public class LoginPageController {
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField connectionField;

    public void loginClick(ActionEvent actionEvent) {
        try {
            // will throw exception if fail to log in
            ModelController.initializeInstance(connectionField.getText(),
                    usernameField.getText(),
                    passwordField.getText());

            Scene scene = ((Node)actionEvent.getSource()).getScene();
            Parent root = FXMLLoader.load(Application.class.getResource("/ui/clients/ClientsPage.fxml"));
            scene.setRoot(root);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
