package ui.controls.insurances.attributetypes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.insurances.attributes.AttributeType;
import ui.controls.AbstractListView;

public class AttributeTypesListView extends AbstractListView<AttributeType> {
    public AttributeTypesListView() {
    }

    public AttributeTypesListView(ObservableList<AttributeType> insuranceTypes) {
        super();
        setItems(insuranceTypes);
    }

    @Override
    protected Node cellGraphicFactory(AttributeType dataObject) {
        return new AttributeTypeView(dataObject);
    }
}
