package ui.pages.agents;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Agent;
import model.ModelController;
import model.UserType;
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
    @FXML private Button managerButton;
    @FXML private Label usernameLabel;

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
            MessageBarController.getInstance().showMessage("Error while accessing database");
        }
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getNaturalPersons();
            List<LegalPerson> legals = ModelController.getInstance().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);
            clientsContainer.getChildren().add(page);
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while accessing database");
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

        updateManagerButton();
    }

    private void updateManagerButton() {
        boolean isManager;
        try {
            isManager = ModelController.getInstance().isAgentManager(data.getAgentId());
        } catch (SQLException e) {
            e.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while accessing database");
            return;
        }

        if (isManager) {
            managerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ModelController.getInstance().managerToAgent(agentView.getData().getAgentId());
                        updateManagerButton();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        MessageBarController.getInstance().showMessage("Error while accessing database");
                    }
                }
            });
            managerButton.setText("Make agent");
        }
        else {
            managerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        ModelController.getInstance().agentToManager(agentView.getData().getAgentId());
                        updateManagerButton();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        MessageBarController.getInstance().showMessage("Error while accessing database");
                    }
                }
            });
            managerButton.setText("Make manager");
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
        UserType currentUser = ModelController.getInstance().getUserType();
        switch (currentUser) {
            case AGENT:
                updateButton.setVisible(false);
                deleteButton.setVisible(false);
                try {
                    if (ModelController.getInstance().getUserObject().equals(data)) {
                        usernameLabel.setText(ModelController.getInstance().getUsername());
                        usernameLabel.setVisible(true);
                    }
                    else
                        usernameLabel.setVisible(false);
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            case MANAGER:
            case ADMIN:
                try {
                    usernameLabel.setText(ModelController.getInstance().getAgentUsername(data.getAgentId()));
                } catch (SQLException e) {
                    e.printStackTrace();
                    MessageBarController.getInstance().showMessage("Error while accessing database");
                }
                usernameLabel.setVisible(true);
                updateButton.setVisible(true);
                deleteButton.setVisible(true);
                break;
        }
    }
}
