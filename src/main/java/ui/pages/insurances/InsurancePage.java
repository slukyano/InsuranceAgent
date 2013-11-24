package ui.pages.insurances;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.ModelController;
import model.UserType;
import model.insurances.Insurance;
import ui.controls.agents.AgentReferenceView;
import ui.controls.clients.ClientReferenceView;
import ui.controls.companies.CompanyReferenceView;
import ui.controls.insurances.InsuranceForm;
import ui.controls.insurances.attributes.AttributesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;
import java.sql.SQLException;

public class InsurancePage extends ViewPage<Insurance> {
    @FXML private Text insuranceTypeName;
    @FXML private AgentReferenceView agentReferenceView;
    @FXML private CompanyReferenceView companyReferenceView;
    @FXML private ClientReferenceView clientReferenceView;
    @FXML private AttributesListView attributesListView;

    public InsurancePage(Insurance data) {
        super(data);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ModelController.getInstance().deleteLegalPerson(getData().getClientId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected EditPage<Insurance> editPageFactory(Insurance dataToEdit) {
        return new EditPage<Insurance>(new InsuranceForm(), dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit insurance";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("InsurancePage.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                insuranceTypeName.setText(data.getInsuranceTypeName());
                agentReferenceView.setData(data.getAgent());
                companyReferenceView.setData(data.getCompany());
                clientReferenceView.setData(data.getClient());
                attributesListView.setItems(FXCollections.observableArrayList(data.getAttributes()));

                UserType currentUser = ModelController.getInstance().getUserType();
                switch (currentUser) {
                    case LEGAL:
                    case NATURAL:
                        agentReferenceView.setClickable(false);
                        updateButton.setVisible(false);
                        deleteButton.setVisible(false);
                        break;

                    case AGENT:
                        if (agentReferenceView.getData().equals(ModelController.getInstance().getUserObject())) {
                            agentReferenceView.setClickable(true);
                            clientReferenceView.setClickable(true);
                        }
                        else {
                            agentReferenceView.setClickable(false);
                            clientReferenceView.setClickable(false);
                        }
                        break;

                    case MANAGER:
                    case ADMIN:
                        agentReferenceView.setClickable(true);
                        clientReferenceView.setClickable(true);
                        break;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            insuranceTypeName.setText("");
            agentReferenceView.setData(null);
            companyReferenceView.setData(null);
            clientReferenceView.setData(null);
            attributesListView.setItems(null);
        }
    }
}
