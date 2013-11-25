package ui.pages.util;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.ModelController;
import ui.MessageBarController;
import ui.UiRootController;

import java.sql.SQLException;

public class ChangePasswordPageController {
    public TextField firstField;
    public TextField secondField;

    public void submitClick(ActionEvent actionEvent) {
        if (firstField.getText().equals(secondField.getText()))
            try {
                ModelController.getInstance().changePassword(firstField.getText());
                UiRootController.getInstance().navigateBack();
            } catch (SQLException e) {
                e.printStackTrace();
                MessageBarController.getInstance().showMessage("Error while connecting to database");
            }
        else
            MessageBarController.getInstance().showMessage("Passwords do not match");
    }
}
