package ui.pages.agents;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Agent;
import model.ModelController;
import model.insurances.Insurance;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentView;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class AgentPage extends ViewPage<Agent> {
    @FXML private AgentView agentView;
    @FXML private Pane insurancesContainer;

    public AgentPage(Agent data) {
        super(data);
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list;
            list=  ModelController.getInstance().getInsurances((Agent)data);
            listView.setItems(FXCollections.observableArrayList(list));
            insurancesContainer.getChildren().add(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
