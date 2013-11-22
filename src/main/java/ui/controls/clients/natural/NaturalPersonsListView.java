package ui.controls.clients.natural;

import javafx.scene.Node;
import model.clients.NaturalPerson;
import ui.controls.AbstractListView;

import java.util.Collection;

public class NaturalPersonsListView extends AbstractListView<NaturalPerson> {
    public NaturalPersonsListView() {
    }

    public NaturalPersonsListView(Collection<NaturalPerson> naturalPersons) {
        super(naturalPersons);
    }

    @Override
    protected Node cellGraphicFactory(NaturalPerson dataObject) {
        return new NaturalPersonView(dataObject);
    }
}
