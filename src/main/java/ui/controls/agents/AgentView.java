package ui.controls.agents;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Agent;
import ui.controls.AbstractView;

import java.net.URL;

public class AgentView extends AbstractView<Agent> {
    @FXML private Text nameField;
    @FXML private Text hireDateField;
    @FXML private Text quitDateField;

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
            quitDateField.setText(data.getQuitDate().toString());
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
