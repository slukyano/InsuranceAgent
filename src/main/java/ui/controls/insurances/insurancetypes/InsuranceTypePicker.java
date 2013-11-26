package ui.controls.insurances.insurancetypes;

import javafx.collections.FXCollections;
import model.Company;
import model.ModelController;
import model.insurances.InsuranceType;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.pages.Matcher;
import ui.pages.SelectPage;

import java.sql.SQLException;
import java.util.List;

public class InsuranceTypePicker extends AbstractPicker<InsuranceType> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    private Company company;

    @Override
    public void pickObject() {
        try {
            InsuranceTypesListView listView = new InsuranceTypesListView();
            List<InsuranceType> list;
            list = company == null
                    ? ModelController.getInstance().getInsuranceTypes()
                    : ModelController.getInstance().getInsuranceTypes(company);
            listView.setItems(FXCollections.observableArrayList(list));

            SelectPage<InsuranceType> page = new SelectPage<InsuranceType>(listView);
            page.setMatcher(new Matcher<InsuranceType>() {
                @Override
                public boolean match(String pattern, InsuranceType object) {
                    return object.getName().contains(pattern);
                }
            });
            page.addSelectionListener(this);

            UiRootController.getInstance().navigateForward(page, "Insurance type select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
