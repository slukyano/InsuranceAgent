package ui.pages.agents;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Agent;
import model.ModelController;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.AnswerListener;
import ui.MessageBarController;
import ui.UiRootController;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentView;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;
import ui.pages.clients.ClientsPage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class AgentPage extends ViewPage<Agent> {
    @FXML private AgentView agentView;
    @FXML private Pane insurancesContainer;
    @FXML private Pane clientsContainer;

    public AgentPage(Agent data) {
        super(data);
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list;
            list=  ModelController.getInstance().getInsurances(data);
            listView.setItems(FXCollections.observableArrayList(list));
            insurancesContainer.getChildren().add(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getNaturalPersons();
            List<LegalPerson> legals = ModelController.getInstance().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);
            clientsContainer.getChildren().add(page);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            MessageBarController.getInstance().showQuestion("Are you sure?",
                    new AnswerListener() {
                        @Override
                        public void yes() {
                            try {
                                ModelController.getInstance().deleteAgent(getData().getAgentId());
                                UiRootController.getInstance().navigateBack();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void no() {
                        }
                    });
            }
        });


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
