package ui.controls.agents;

import javafx.collections.FXCollections;
import model.Agent;
import model.ModelController;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.controls.SelectionListener;

import java.sql.SQLException;

public class AgentPicker extends AbstractPicker<Agent> {
    @Override
    protected String getValueString() {
        return data.getFullName();
    }

    @Override
    public void pickObject() {
        try {
            AgentsListView listView = new AgentsListView();
            listView.setItems(FXCollections.observableArrayList(ModelController.getInstance().getAgents()));
            listView.addSelectionListener(new SelectionListener<Agent>() {
                @Override
                public void objectSelected(Agent selectedT) {
                    setData(selectedT);
                }
            });

            UiRootController.getInstance().NavigateForward(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
