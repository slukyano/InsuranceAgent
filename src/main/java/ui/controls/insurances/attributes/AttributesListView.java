package ui.controls.insurances.attributes;

import javafx.scene.Node;
import model.insurances.attributes.InsuranceAttribute;
import ui.controls.AbstractListView;

import java.util.Collection;

public class AttributesListView extends AbstractListView<InsuranceAttribute> {
    public AttributesListView() {
    }

    public AttributesListView(Collection<InsuranceAttribute> insuranceAttributes) {
        super(insuranceAttributes);
    }

    @Override
    protected Node cellGraphicFactory(InsuranceAttribute dataObject) {
        return new AttributeView(dataObject);
    }
}
