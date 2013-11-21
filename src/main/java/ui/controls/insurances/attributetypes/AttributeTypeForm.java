package ui.controls.insurances.attributetypes;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Agent;
import ui.controls.AbstractForm;

import java.net.URL;
import java.sql.SQLException;

public class AttributeTypeForm extends AbstractForm<Agent> {
    @FXML private TextField firstNameField;
    @FXML private TextField secondNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker hireDatePicker;
    @FXML private DatePicker quitDatePicker;

    @Override
    public Agent createObject() {
        return null;
    }

    @Override
    public Agent updateObject() throws SQLException {
        return null;
    }

    @Override
    public void clearForm() {
        firstNameField.setText("");
        secondNameField.setText("");
        lastNameField.setText("");
        hireDatePicker.setSelectedDate(null);
        quitDatePicker.setSelectedDate(null);
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AgentForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            firstNameField.setText(data.getFirstName());
            secondNameField.setText(data.getSecondName());
            lastNameField.setText(data.getLastName());
            hireDatePicker.setSelectedDate(data.getHiringDate());
            quitDatePicker.setSelectedDate(data.getQuitDate());
        }
        else {
            clearForm();
        }
    }
}
