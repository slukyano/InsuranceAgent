package ui.controls.agents;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.Agent;
import ui.controls.AbstractListView;

public class AgentsListView extends AbstractListView<Agent> {
    public AgentsListView() {
    }

    public AgentsListView(ObservableList<Agent> agents) {
        super();
        setItems(agents);
    }

    @Override
    protected Node cellGraphicFactory(Agent dataObject) {
        return new AgentView(dataObject);
    }
}
