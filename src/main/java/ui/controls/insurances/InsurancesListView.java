package ui.controls.insurances;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.insurances.Insurance;
import ui.controls.AbstractListView;

public class InsurancesListView extends AbstractListView<Insurance> {
    public InsurancesListView() {
    }

    public InsurancesListView(ObservableList<Insurance> insurances) {
        super();
        setItems(insurances);
    }

    @Override
    protected Node cellGraphicFactory(Insurance dataObject) {
        return new InsuranceView(dataObject);
    }
}
