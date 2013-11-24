package ui.controls.clients.natural;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ModelController;
import model.clients.NaturalPerson;
import ui.controls.AbstractForm;
import ui.controls.agents.AgentPicker;

import java.net.URL;
import java.sql.SQLException;

public class NaturalPersonForm extends AbstractForm<NaturalPerson> {
    @FXML private TextField firstNameField;
    @FXML private TextField secondNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker dateOfBirthPicker;
    @FXML private AgentPicker agentPicker;

    @Override
    public NaturalPerson createObject() throws SQLException {
        return ModelController.getInstance().createNaturalPerson(
                firstNameField.getText(),
                secondNameField.getText(),
                lastNameField.getText(),
                dateOfBirthPicker.getSelectedDate(),
                agentPicker.getData().getAgentId());
    }

    @Override
    public NaturalPerson updateObject() throws SQLException {
        return null;
    }

    @Override
    public void clearForm() {
        firstNameField.setText("");
        secondNameField.setText("");
        lastNameField.setText("");
        dateOfBirthPicker.setSelectedDate(null);
        agentPicker.setData(null);
    }

    @Override
    public boolean canSubmit() {
        return !firstNameField.getText().isEmpty()
                && !lastNameField.getText().isEmpty()
                && dateOfBirthPicker.getSelectedDate() != null
                && agentPicker.getData() != null;
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("NaturalPersonForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                firstNameField.setText(data.getFirstName());
                secondNameField.setText(data.getSecondName());
                lastNameField.setText(data.getLastName());
                dateOfBirthPicker.setSelectedDate(data.getDateOfBirth());
                agentPicker.setData(data.getAgent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            clearForm();
    }
}
