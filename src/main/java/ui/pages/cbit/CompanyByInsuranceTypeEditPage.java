package ui.pages.cbit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Company;
import model.ModelController;
import model.insurances.CompanyByInsuranceType;
import model.insurances.InsuranceType;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.insurances.attributetypes.AttributeTypeFormsList;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;

public class CompanyByInsuranceTypeEditPage extends Pane implements SelectionProvider<CompanyByInsuranceType> {
    @FXML public Label companyNameText;
    @FXML public Label insuranceTypeNameText;
    @FXML public Button addTypeButton;
    @FXML public Button submitButton;
    @FXML public AttributeTypeFormsList formsList;
    private HashSet<SelectionListener<CompanyByInsuranceType>> listeners =
            new HashSet<SelectionListener<CompanyByInsuranceType>>();
    private Company company;
    private InsuranceType insuranceType;


    public CompanyByInsuranceTypeEditPage(Company company, InsuranceType insuranceType) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyByInsuranceTypeEditPage.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.company = company;
        this.insuranceType = insuranceType;

        companyNameText.setText(company.getName());
        insuranceTypeNameText.setText(insuranceType.getName());

        addTypeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                formsList.addAttributeType();
            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CompanyByInsuranceType cbit = createObject();
                    notifyListeners(cbit);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected CompanyByInsuranceType createObject() throws SQLException {
        CompanyByInsuranceType cbit = ModelController.getInstance().createCompanyByInsuranceType(
                company.getCompanyId(),
                insuranceType.getInsuranceTypeId());
        formsList.commit(cbit.getCompanyByInsuranceTypeId());
        return cbit;
    }

    public void clearForm() {
        formsList.clearForms();
    }

    protected URL getFxmlUrl() {
        return getClass().getResource("CompanyByInsuranceTypeEditPage.fxml");
    }

    private void notifyListeners(CompanyByInsuranceType selectedObject) {
        for (SelectionListener<CompanyByInsuranceType> listener : listeners)
            listener.objectSelected(this, selectedObject);
    }

    @Override
    public void addSelectionListener(SelectionListener<CompanyByInsuranceType> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSelectionListener(SelectionListener<CompanyByInsuranceType> listener) {
        listeners.remove(listener);
    }
}
