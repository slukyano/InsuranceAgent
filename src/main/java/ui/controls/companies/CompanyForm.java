package ui.controls.companies;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Company;
import model.ModelController;
import ui.controls.AbstractForm;

import java.net.URL;
import java.sql.SQLException;

public class CompanyForm extends AbstractForm<Company> {
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;

    @Override
    public Company createObject() throws SQLException {
        return ModelController.getInstance().createCompany(
                nameField.getText(),
                null,
                descriptionField.getText());
    }

    @Override
    public Company updateObject() throws SQLException {
        return ModelController.getInstance().updateCompany(
                nameField.getText(),
                null,
                descriptionField.getText(),
                data.getCompanyId());
    }

    @Override
    public void clearForm() {
        nameField.setText("");
    }

    @Override
    public boolean canSubmit() {
        return !nameField.getText().isEmpty();
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("CompanyForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getName());
        }
        else
            clearForm();
    }
}
