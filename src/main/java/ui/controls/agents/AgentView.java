package ui.controls.agents;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Agent;
import ui.controls.AbstractView;

import java.net.URL;
import java.util.Date;

public class AgentView extends AbstractView<Agent> {
    @FXML private Label nameField;
    @FXML private Label hireDateField;
    @FXML private Label quitDateField;

    public AgentView() {
    }

    public AgentView(Agent data) {
        super(data);
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getFullName());
            hireDateField.setText(data.getHiringDate().toString());
            Date quitDate = data.getQuitDate();
            quitDateField.setText(quitDate != null ? data.getQuitDate().toString() : "");
        }
        else {
            nameField.setText("");
            hireDateField.setText("");
            quitDateField.setText("");
        }
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AgentView.fxml");
    }
}
