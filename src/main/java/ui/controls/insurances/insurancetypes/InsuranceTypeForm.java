package ui.controls.insurances.insurancetypes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ModelController;
import model.insurances.InsuranceType;
import ui.controls.AbstractForm;

import java.net.URL;
import java.sql.SQLException;

public class InsuranceTypeForm extends AbstractForm<InsuranceType> {
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;

    @Override
    public InsuranceType createObject() throws SQLException {
        return ModelController.getInstance().createInsuranceType(
                nameField.getText(),
                descriptionField.getText());
    }

    @Override
    public InsuranceType updateObject() throws SQLException {
        return ModelController.getInstance().updateInsuranceType(
                nameField.getText(),
                descriptionField.getText(),
                data.getInsuranceTypeId());
    }

    @Override
    public void clearForm() {
        nameField.setText("");
        descriptionField.setText("");
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("InsuranceTypeForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getName());
            descriptionField.setText(data.getDescription());
        }
        else {
            clearForm();
        }
    }
}
