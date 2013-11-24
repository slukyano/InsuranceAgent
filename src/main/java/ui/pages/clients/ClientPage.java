package ui.pages.clients;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.ModelController;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.controls.AbstractForm;
import ui.controls.AbstractView;
import ui.controls.agents.AgentReferenceView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonView;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ClientPage extends ViewPage<Client> {
    @FXML private Pane viewContainer;
    @FXML private AgentReferenceView agentReferenceView;
    @FXML private Pane insurancesContainer;
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
            insurancesContainer.getChildren().add(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                if(getData().getClientType().equals("NATURAL"))
                    ModelController.getInstance().deleteNaturalPerson(getData().getClientId());
                else
                    ModelController.getInstance().deleteLegalPerson(getData().getClientId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
