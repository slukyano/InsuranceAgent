package ui.pages.clients;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.ModelController;
import model.UserType;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.AnswerListener;
import ui.MessageBarController;
import ui.UiRootController;
import ui.controls.AbstractForm;
import ui.controls.AbstractView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentReferenceView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonView;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;
import ui.pages.insurances.InsurancePage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ClientPage extends ViewPage<Client> {
    @FXML private Pane viewContainer;
    @FXML private AgentReferenceView agentReferenceView;
    @FXML private StackPane insurancesContainer;
    private AbstractView clientView;

    public ClientPage(Client data) {
        super();

        clientView = data.getClientType().equals("NATURAL")
                ? new NaturalPersonView((NaturalPerson)data)
                : new LegalPersonView((LegalPerson)data);
        viewContainer.getChildren().add(clientView);
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list;
            list= data.getClientType().equals("NATURAL")
                    ? ModelController.getInstance().getInsurances((NaturalPerson)data)
                    : ModelController.getInstance().getInsurances((LegalPerson)data);
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(new SelectionListener<Insurance>() {
                @Override
                public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                    InsurancePage page = new InsurancePage(selectedObject);
                    UiRootController.getInstance().navigateForward(page, selectedObject.getInsuranceTypeName());
                }
            });
            insurancesContainer.getChildren().add(listView);
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
                                    if(getData().getClientType().equals("NATURAL"))
                                        ModelController.getInstance().deleteNaturalPerson(getData().getClientId());
                                    else
                                        ModelController.getInstance().deleteLegalPerson(getData().getClientId());
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

        setData(data);
    }

    @Override
    protected EditPage<Client> editPageFactory(Client dataToEdit) {
        AbstractForm form = dataToEdit.getClientType().equals("NATURAL")
                ? new NaturalPersonForm()
                : new LegalPersonForm();
        return new EditPage<Client>(form, dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit client";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("/ui/pages/clients/ClientPage.fxml");
    }

    @Override
    protected void update() {
        try {
            clientView.setData(data);
            agentReferenceView.setData(data.getAgent());

            UserType currentUser = ModelController.getInstance().getUserType();
            if (currentUser == UserType.LEGAL || currentUser == UserType.NATURAL) {
                agentReferenceView.setClickable(false);
                updateButton.setVisible(false);
                deleteButton.setVisible(false);
            }
            else {
                agentReferenceView.setClickable(true);
                updateButton.setVisible(true);
                deleteButton.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
