package ui.controls.agents;

import javafx.collections.FXCollections;
import model.Agent;
import model.ModelController;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

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
            listView.addSelectionListener(new SelectionListener<Agent>() {
                @Override
                public void objectSelected(SelectionProvider<Agent> provider, Agent selectedObject) {
                    setData(selectedObject);
                    UiRootController.getInstance().navigateBack();
                }
            });
            UiRootController.getInstance().navigateForward(listView, "Agent select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
