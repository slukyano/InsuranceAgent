package ui.controls.insurances.insurancetypes;

import javafx.collections.FXCollections;
import model.ModelController;
import model.insurances.InsuranceType;
import ui.UiRootController;
import ui.controls.AbstractPicker;

import java.sql.SQLException;

public class InsuranceTypePicker extends AbstractPicker<InsuranceType> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    @Override
    public void pickObject() {
        try {
            InsuranceTypesListView listView = new InsuranceTypesListView();
            listView.setItems(FXCollections.observableArrayList(ModelController.getInstance().getInsuranceTypes()));
            listView.addSelectionListener(this);

            UiRootController.getInstance().navigateForward(listView, "Insurance type select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
