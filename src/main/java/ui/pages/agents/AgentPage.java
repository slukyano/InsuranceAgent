package ui.pages.agents;

import model.Agent;
import ui.controls.AbstractView;
import ui.controls.agents.AgentForm;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class AgentPage extends ViewPage<Agent> {
    public AgentPage(AbstractView<Agent> objectView, Agent data) {
        super(objectView, data);
    }

    @Override
    protected EditPage<Agent> editPageFactory(Agent dataToEdit) {
        return new EditPage<Agent>(new AgentForm(), dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit agent";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AgentPage.fxml");
    }
}
