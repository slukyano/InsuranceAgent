package ui.controls.insurances.attributes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.insurances.attributes.InsuranceAttribute;
import ui.controls.AbstractListView;

public class AttributesListView extends AbstractListView<InsuranceAttribute> {
    public AttributesListView() {
    }

    public AttributesListView(ObservableList<InsuranceAttribute> insuranceAttributes) {
        super();
        setItems(insuranceAttributes);
    }

    @Override
    protected Node cellGraphicFactory(InsuranceAttribute dataObject) {
        return new AttributeView(dataObject);
    }
}
