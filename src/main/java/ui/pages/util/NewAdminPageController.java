package ui.pages.util;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.ModelController;
import ui.MessageBarController;
import ui.UiRootController;

import java.sql.SQLException;

public class NewAdminPageController {
    public TextField usernameField;

    public void submitClicked(ActionEvent actionEvent) {
        try {
            ModelController.getInstance().createAdmin(usernameField.getText());
            UiRootController.getInstance().navigateBack();
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while connecting to database");
        }
    }
}
