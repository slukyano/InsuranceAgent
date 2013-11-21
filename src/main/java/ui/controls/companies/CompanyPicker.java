package ui.controls.companies;

import javafx.collections.FXCollections;
import model.Company;
import model.ModelController;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.controls.SelectionListener;

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
            listView.addSelectionListener(new SelectionListener<Company>() {
                @Override
                public void objectSelected(Company selectedT) {
                    setData(selectedT);
                }
            });

            UiRootController.getInstance().NavigateForward(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
