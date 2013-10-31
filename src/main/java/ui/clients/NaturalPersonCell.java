package ui.clients;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.clients.NaturalPerson;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 31.10.13
 */
public class NaturalPersonCell extends ListCell<NaturalPerson> {
    @FXML public Text nameField;
    @FXML public Text birthField;
    @FXML public GridPane gridPane;

    public NaturalPersonCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NaturalPersonCell.fxml"));
        //fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    protected void updateItem(NaturalPerson naturalPerson, boolean b) {
        super.updateItem(naturalPerson, b);

        if (naturalPerson != null) {
            nameField.setText(String.format("%s %s %s", naturalPerson.getFirstName(),
                    naturalPerson.getSecondName(), naturalPerson.getLastName()));
            birthField.setText(naturalPerson.getDateOfBirth().toString());
            setGraphic(gridPane);
        }
    }
}

