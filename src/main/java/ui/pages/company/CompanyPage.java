package ui.pages.company;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Company;
import model.ModelController;
import model.insurances.CompanyByInsuranceType;
import model.insurances.InsuranceType;
import ui.UiRootController;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.companies.CompanyForm;
import ui.controls.companies.CompanyView;
import ui.controls.insurances.insurancetypes.InsuranceTypesListView;
import ui.pages.EditPage;
import ui.pages.ViewPage;
import ui.pages.cbit.CompanyByInsuranceTypeEditPage;

import java.net.URL;
import java.sql.SQLException;

public class CompanyPage extends ViewPage<Company> {
    @FXML private CompanyView companyView;
    @FXML private Button addInsuranceTypeButton;

    public CompanyPage(final Company data) {
        super(data);

        addInsuranceTypeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    InsuranceTypesListView listView = new InsuranceTypesListView();
                    listView.setItems(
                            FXCollections.observableArrayList(
                                    ModelController.getInstance().getInsuranceTypes()));
                    listView.addSelectionListener(new SelectionListener<InsuranceType>() {
                        @Override
                        public void objectSelected(SelectionProvider<InsuranceType> provider,
                                                   InsuranceType selectedObject) {
                            CompanyByInsuranceTypeEditPage editPage
                                    = new CompanyByInsuranceTypeEditPage(data, selectedObject);
                            editPage.addSelectionListener(new SelectionListener<CompanyByInsuranceType>() {
                                @Override
                                public void objectSelected(SelectionProvider<CompanyByInsuranceType> provider,
                                                           CompanyByInsuranceType selectedObject) {
                                    UiRootController.getInstance().navigateBack();
                                    UiRootController.getInstance().navigateBack();
                                }
                            });

                            UiRootController.getInstance().navigateForward(editPage, selectedObject.getName());
                        }
                    });

                    UiRootController.getInstance().navigateForward(listView, "Select insurance type");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected EditPage<Company> editPageFactory(Company dataToEdit) {
        return new EditPage<Company>(new CompanyForm(), dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit company";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("CompanyPage.fxml");
    }

    @Override
    protected void update() {
        companyView.setData(data);
    }
}
