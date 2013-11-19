package ui.controls.clients.legal;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.clients.LegalPerson;
import ui.controls.AbstractListView;

public class LegalPersonListView extends AbstractListView<LegalPerson> {
    public LegalPersonListView() {
    }

    public LegalPersonListView(ObservableList<LegalPerson> legalPersons) {
        super();
        setItems(legalPersons);
    }

    @Override
    protected Node cellGraphicFactory(LegalPerson dataObject) {
        return new LegalPersonView(dataObject);
    }
}
