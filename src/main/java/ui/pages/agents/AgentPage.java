package ui.pages.agents;

import javafx.fxml.FXML;
import model.Agent;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class AgentPage extends ViewPage<Agent> {
    @FXML private AgentView agentView;

    public AgentPage(Agent data) {
        super(data);
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

    @Override
    protected void update() {
        agentView.setData(data);
    }
}
