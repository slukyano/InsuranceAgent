package ui.controls.clients;

import javafx.collections.FXCollections;
import model.ModelController;
import model.clients.Client;
import model.clients.NaturalPerson;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.clients.natural.NaturalPersonsListView;

import java.sql.SQLException;

public class ClientPicker extends AbstractPicker<Client> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    @Override
    public void pickObject() {
        try {
            NaturalPersonsListView listView = new NaturalPersonsListView();
            listView.setItems(FXCollections.observableArrayList(ModelController.getInstance().getNaturalPersons()));
            listView.addSelectionListener(new SelectionListener<NaturalPerson>() {
                @Override
                public void objectSelected(SelectionProvider<NaturalPerson> provider, NaturalPerson selectedObject) {
                    setData(selectedObject);
                }
            });

            UiRootController.getInstance().navigateForward(listView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
