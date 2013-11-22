package ui.controls.companies;

import javafx.collections.FXCollections;
import model.Company;
import model.ModelController;
import ui.UiRootController;
import ui.controls.AbstractPicker;

import java.sql.SQLException;

public class CompanyPicker extends AbstractPicker<Company> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    @Override
    public void pickObject() {
        try {
            CompaniesListView listView = new CompaniesListView();
            listView.setItems(FXCollections.observableArrayList(ModelController.getInstance().getCompanies()));
            listView.addSelectionListener(this);

            UiRootController.getInstance().navigateForward(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}