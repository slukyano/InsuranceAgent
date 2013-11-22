package ui.controls.agents;

import javafx.collections.FXCollections;
import model.Agent;
import model.ModelController;
import ui.UiRootController;
import ui.controls.AbstractPicker;

import java.sql.SQLException;
import java.util.List;

public class AgentPicker extends AbstractPicker<Agent> {
    @Override
    protected String getValueString() {
        return data.getFullName();
    }

    @Override
    public void pickObject() {
        pickObject(false);
    }

    public void pickObject(boolean currentlyWorking) {
        try {
            AgentsListView listView = new AgentsListView();
            List<Agent> list = currentlyWorking
                    ? ModelController.getInstance().getAgents(true)
                    : ModelController.getInstance().getAgents(false);
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(this);

            UiRootController.getInstance().navigateForward(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
