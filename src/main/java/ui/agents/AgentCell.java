package ui.agents;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Agent;

import java.io.IOException;

public class AgentCell extends ListCell<Agent> {
    @FXML public Text nameField;
    @FXML public VBox rootVBox;
    @FXML public Text hireDate;

    public AgentCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgentCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(Agent agent, boolean b) {
        super.updateItem(agent, b);

        if (agent != null) {
            nameField.setText(agent.getFullName());
            hireDate.setText(agent.getHiringDate().toString());
            setGraphic(rootVBox);
        }
    }
}
