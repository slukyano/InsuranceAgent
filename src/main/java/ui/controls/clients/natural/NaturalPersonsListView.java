package ui.controls.clients.natural;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.clients.NaturalPerson;
import ui.controls.AbstractListView;

public class NaturalPersonsListView extends AbstractListView<NaturalPerson> {
    public NaturalPersonsListView() {
    }

    public NaturalPersonsListView(ObservableList<NaturalPerson> naturalPersons) {
        super();
        setItems(naturalPersons);
    }

    @Override
    protected Node cellGraphicFactory(NaturalPerson dataObject) {
        return new NaturalPersonView(dataObject);
    }
}
