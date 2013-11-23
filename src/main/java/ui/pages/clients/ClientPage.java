package ui.pages.clients;

import model.clients.Client;
import ui.controls.AbstractForm;
import ui.controls.AbstractView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class ClientPage extends ViewPage<Client> {
    public ClientPage(AbstractView<Client> objectView, Client data) {
        super(objectView, data);
    }

    @Override
    protected EditPage<Client> editPageFactory(Client dataToEdit) {
        AbstractForm form = dataToEdit.getClientType().equals("NATURAL")
                ? new NaturalPersonForm()
                : new LegalPersonForm();
        return new EditPage<Client>(form, dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit client";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("/ui/pages/clients/ClientPage.fxml");
    }
}
