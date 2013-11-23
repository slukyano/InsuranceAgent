package ui.controls.clients;

import model.clients.Client;
import ui.UiRootController;
import ui.controls.AbstractReferenceView;
import ui.pages.clients.ClientPage;

public class ClientReferenceView extends AbstractReferenceView<Client> {
    public ClientReferenceView() {
    }

    public ClientReferenceView(Client data) {
        super(data);
    }

    @Override
    protected void onMouseClicked() {
        UiRootController.getInstance().navigateForward(
                new ClientPage(data),
                data.getName());
    }

    @Override
    protected String getValueString() {
        return data != null ? data.getName() : "";
    }
}
