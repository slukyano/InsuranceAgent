package ui.controls.clients.legal;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.clients.LegalPerson;
import ui.controls.AbstractView;

import java.net.URL;

public class LegalPersonView extends AbstractView<LegalPerson> {
    @FXML private Text nameField;
    @FXML private Text vatinField;
    @FXML private Text addressField;

    public LegalPersonView() {
    }

    public LegalPersonView(LegalPerson data) {
        super(data);
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getName());
            vatinField.setText(data.getVatin());
            addressField.setText(data.getAddress());
        }
        else {
            nameField.setText("");
            vatinField.setText("");
            addressField.setText("");
        }
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("LegalPersonView.fxml");
    }
}
