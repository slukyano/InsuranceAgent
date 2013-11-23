package ui.pages.insurances;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.insurances.Insurance;
import ui.controls.agents.AgentReferenceView;
import ui.controls.clients.ClientReferenceView;
import ui.controls.companies.CompanyReferenceView;
import ui.controls.insurances.InsuranceForm;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;
import java.sql.SQLException;

public class InsurancePage extends ViewPage<Insurance> {
    @FXML private Text insuranceTypeName;
    @FXML private AgentReferenceView agentReferenceView;
    @FXML private CompanyReferenceView companyReferenceView;
    @FXML private ClientReferenceView clientReferenceView;

    public InsurancePage(Insurance data) {
        super(data);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            insuranceTypeName.setText("");
            agentReferenceView.setData(null);
            companyReferenceView.setData(null);
            clientReferenceView.setData(null);
        }
    }
}
