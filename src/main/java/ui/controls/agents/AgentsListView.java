package ui.controls.agents;

import javafx.scene.Node;
import model.Agent;
import ui.controls.AbstractListView;

import java.util.Collection;

public class AgentsListView extends AbstractListView<Agent> {
    public AgentsListView() {
    }

    public AgentsListView(Collection<Agent> agents) {
        super(agents);
    }

    @Override
    protected Node cellGraphicFactory(Agent dataObject) {
        return new AgentView(dataObject);
    }
}
