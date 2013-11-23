package ui.controls.insurances;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import model.Company;
import model.ModelController;
import model.insurances.CompanyByInsuranceType;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
import ui.controls.AbstractForm;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentPicker;
import ui.controls.clients.ClientPicker;
import ui.controls.companies.CompanyPicker;
import ui.controls.insurances.attributes.AttributeFormsList;
import ui.controls.insurances.insurancetypes.InsuranceTypePicker;

import java.net.URL;
import java.sql.SQLException;

public class InsuranceForm extends AbstractForm<Insurance> {
    @FXML private ClientPicker clientPicker;
    @FXML private InsuranceTypePicker typePicker;
    @FXML private CompanyPicker companyPicker;
    @FXML private AgentPicker agentPicker;
    @FXML private Pane attributeFormsContainer;
    private CompanyByInsuranceType pickedCbit;
    private AttributeFormsList attributeFormsList;

    public InsuranceForm() {
        typePicker.addSelectionListener(new SelectionListener<InsuranceType>() {
            @Override
            public void objectSelected(SelectionProvider<InsuranceType> provider, InsuranceType selectedObject) {
                onCompanyOrTypePicked();
            }
        });

        companyPicker.addSelectionListener(new SelectionListener<Company>() {
            @Override
            public void objectSelected(SelectionProvider<Company> provider, Company selectedObject) {
                onCompanyOrTypePicked();
            }
        });
    }

    public InsuranceForm(Insurance data) {
        super(data);
    }

    private void onCompanyOrTypePicked() {
        if (typePicker.getData() != null && companyPicker.getData() != null) {
            try {
                pickedCbit = ModelController.getInstance().getCompanyByInsuranceType(
                        companyPicker.getData().getCompanyId(),
                        typePicker.getData().getInsuranceTypeId());

                setAttributeFormsList(new AttributeFormsList(pickedCbit));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private AttributeFormsList getAttributeFormsList() {
        return attributeFormsList;
    }

    private void setAttributeFormsList(AttributeFormsList attributeFormsList) {
        this.attributeFormsList = attributeFormsList;
        attributeFormsContainer.getChildren().clear();
        attributeFormsContainer.getChildren().add(attributeFormsList);
    }

    @Override
    public Insurance createObject() throws SQLException {
        Insurance insurance = ModelController.getInstance().createInsurance(
                clientPicker.getData().getClientId(),
                clientPicker.getData().getClientType(),
                pickedCbit.getCompanyByInsuranceTypeId(),
                agentPicker.getData().getAgentId());

        attributeFormsList.commit(insurance.getInsuranceId());

        return insurance;
    }

    @Override
    public Insurance updateObject() throws SQLException {
        Insurance insurance = ModelController.getInstance().updateInsurance(
                clientPicker.getData().getClientId(),
                clientPicker.getData().getClientType(),
                data.getCompanyByInsuranceTypeId(),
                agentPicker.getData().getAgentId(),
                data.getInsuranceId());

        attributeFormsList.commit(insurance.getInsuranceId());

        return insurance;
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
                setAttributeFormsList(new AttributeFormsList(data));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
            clearForm();
    }
}
