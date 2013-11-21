package ui.controls.insurances.attributetypes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.insurances.InsuranceType;
import ui.controls.AbstractListView;

public class AttributeTypesListView extends AbstractListView<InsuranceType> {
    public AttributeTypesListView() {
    }

    public AttributeTypesListView(ObservableList<InsuranceType> insuranceTypes) {
        super();
        setItems(insuranceTypes);
    }

    @Override
    protected Node cellGraphicFactory(InsuranceType dataObject) {
        return new AttributeTypeView(dataObject);
    }
}
