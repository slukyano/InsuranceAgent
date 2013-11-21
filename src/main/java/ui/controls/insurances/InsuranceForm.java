package ui.controls.insurances;

import javafx.fxml.FXML;
import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.Client;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
import ui.controls.AbstractForm;
import ui.controls.agents.AgentPicker;
import ui.controls.clients.ClientPicker;
import ui.controls.companies.CompanyPicker;
import ui.controls.insurances.insurancetypes.InsuranceTypePicker;

import java.net.URL;
import java.sql.SQLException;

public class InsuranceForm extends AbstractForm<Insurance> {
    @FXML private ClientPicker clientPicker;
    @FXML private InsuranceTypePicker typePicker;
    @FXML private CompanyPicker companyPicker;
    @FXML private AgentPicker agentPicker;

    @Override
    public Insurance createObject() throws SQLException {
        Client client = clientPicker.getData();
        InsuranceType type = typePicker.getData();
        Company company = companyPicker.getData();
        Agent agent = agentPicker.getData();
        return ModelController.getInstance().createInsurance(
                clientPicker.getData().getClientId(),
                clientPicker.getData().getClientType(),
                typePicker.getData().getInsuranceTypeId(),
                companyPicker.getData().getCompanyId(),
                agentPicker.getData().getAgentId());
    }

    @Override
    public Insurance updateObject() throws SQLException {
        return null;
    }

    @Override
    public void clearForm() {
        clientPicker.setData(null);
        typePicker.setData(null);
        companyPicker.setData(null);
        agentPicker.setData(null);
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("InsuranceForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                clientPicker.setData(data.getClient());
                typePicker.setData(data.getInsuranceType());
                companyPicker.setData(data.getCompany());
                agentPicker.setData(data.getAgent());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            clearForm();
    }
}
