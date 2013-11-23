package ui.pages.clients;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.ModelController;
import model.clients.Client;
import model.insurances.Insurance;
import ui.UiRootController;
import ui.controls.AbstractForm;
import ui.controls.AbstractView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.insurances.InsuranceView;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class ClientPage extends ViewPage<Client> {
    @FXML
    protected Button insurancesButton;
    @FXML
    protected Button agentButton;
    public ClientPage(AbstractView<Client> objectView, Client data) {
        super(objectView, data);

        insurancesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    InsurancesListView listView = new InsurancesListView();
                    List<Insurance> list = ModelController.getInstance().getInsurances();
                    listView.setItems(FXCollections.observableArrayList(list));
                    listView.addSelectionListener(new SelectionListener<Insurance>() {
                        @Override
                        public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                            UiRootController.getInstance().navigateForward(new InsuranceView(selectedObject),
                                    selectedObject.getInsuranceTypeName());
                        }
                    });
                    UiRootController.getInstance().navigateForward(listView, "Insurances");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        agentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    UiRootController.getInstance().navigateForward(new AgentView(getData().getAgent()),
                            getData().getAgent().getFullName());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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
}
