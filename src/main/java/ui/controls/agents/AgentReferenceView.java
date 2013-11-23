package ui.controls.agents;

import model.Agent;
import ui.UiRootController;
import ui.controls.AbstractReferenceView;
import ui.pages.agents.AgentPage;

public class AgentReferenceView extends AbstractReferenceView<Agent> {
    public AgentReferenceView() {
    }

    public AgentReferenceView(Agent data) {
        super(data);
    }

    @Override
    protected void onMouseClicked() {
        UiRootController.getInstance().navigateForward(
                new AgentPage(data),
                data.getFullName());
    }

    @Override
    protected String getValueString() {
        return data != null ? data.getFullName() : "";
    }
}
