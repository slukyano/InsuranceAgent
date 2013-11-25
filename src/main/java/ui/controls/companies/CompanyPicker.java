package ui.controls.companies;

import javafx.collections.FXCollections;
import model.Company;
import model.ModelController;
import model.insurances.InsuranceType;
import ui.UiRootController;
import ui.controls.AbstractPicker;

import java.sql.SQLException;
import java.util.List;

public class CompanyPicker extends AbstractPicker<Company> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    private InsuranceType type;


    @Override
    public void pickObject() {
        try {
            CompaniesListView listView = new CompaniesListView();
            List<Company> list;
            list = type == null
                    ? ModelController.getInstance().getCompanies()
                    : ModelController.getInstance().getCompanies(type.getInsuranceTypeId());
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(this);

            UiRootController.getInstance().navigateForward(listView, "Company select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public InsuranceType getType() {
        return type;
    }

    public void setType(InsuranceType type) {
        this.type = type;
    }
}
