package ui.controls.clients.legal;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ModelController;
import model.clients.LegalPerson;
import ui.controls.AbstractForm;
import ui.controls.agents.AgentPicker;

import java.net.URL;
import java.sql.SQLException;

public class LegalPersonForm extends AbstractForm<LegalPerson> {
    @FXML private TextField nameField;
    @FXML private TextField addressField;
    @FXML private TextField vatinField;
    @FXML private AgentPicker agentPicker;

    @Override
    public LegalPerson createObject() throws SQLException {
        return ModelController.getInstance().createLegalPerson(
                nameField.getText(),
                addressField.getText(),
                vatinField.getText(),
                agentPicker.getData().getAgentId());
    }

    @Override
    public LegalPerson updateObject() throws SQLException {
        return null;
    }

    @Override
    public void clearForm() {
        nameField.setText("");
        addressField.setText("");
        vatinField.setText("");
        agentPicker.setData(null);
    }

    @Override
    public boolean canSubmit() {
        return !nameField.getText().isEmpty()
                && !addressField.getText().isEmpty()
                && !vatinField.getText().isEmpty()
                && agentPicker.getData() != null;
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("LegalPersonForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                nameField.setText(data.getName());
                addressField.setText(data.getAddress());
                vatinField.setText(data.getVatin());
                agentPicker.setData(data.getAgent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            clearForm();
    }
}
