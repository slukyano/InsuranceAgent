package ui.pages.insurances;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import ui.controls.insurances.InsuranceForm;

import java.sql.SQLException;

public class InsuranceUpdatePageController {
    public InsuranceForm form;
    public Button createButton;

    public void createClick(ActionEvent actionEvent) {
        try {
            form.createObject();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
