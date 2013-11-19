package ui.controls.insurances.types;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.insurances.InsuranceType;
import ui.controls.AbstractListView;

public class InsuranceTypesListView extends AbstractListView<InsuranceType> {
    public InsuranceTypesListView() {
    }

    public InsuranceTypesListView(ObservableList<InsuranceType> insuranceTypes) {
        super();
        setItems(insuranceTypes);
    }

    @Override
    protected Node cellGraphicFactory(InsuranceType dataObject) {
        return new InsuranceTypeView(dataObject);
    }
}
