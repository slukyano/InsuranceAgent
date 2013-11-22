package ui.controls.clients;

import model.ModelController;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import ui.UiRootController;
import ui.controls.AbstractPicker;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.pages.clients.ClientsPage;

import java.sql.SQLException;
import java.util.List;

public class ClientPicker extends AbstractPicker<Client> {
    @Override
    protected String getValueString() {
        return data.getName();
    }

    @Override
    public void pickObject() {
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getNaturalPersons();
            List<LegalPerson> legals = ModelController.getInstance().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);
            page.addSelectionListener(new SelectionListener<Client>() {
                @Override
                public void objectSelected(SelectionProvider<Client> provider, Client selectedObject) {
                    setData(selectedObject);
                    UiRootController.getInstance().navigateBack();
                }
            });

            UiRootController.getInstance().navigateForward(page, "Client select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
