package ui.clients;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.clients.LegalPerson;

import java.io.IOException;

public class LegalPersonCell extends ListCell<LegalPerson> {
    @FXML public Text nameField;
    @FXML public Text vatinField;
    @FXML public Text addressField;
    @FXML public GridPane gridPane;

    public LegalPersonCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LegalPersonCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(LegalPerson legalPerson, boolean b) {
        super.updateItem(legalPerson, b);

        if (legalPerson != null) {
            nameField.setText(legalPerson.getName());
            vatinField.setText(legalPerson.getVatin());
            addressField.setText(legalPerson.getAddress());
            setGraphic(gridPane);
        }
    }
}

