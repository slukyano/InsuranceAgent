package ui.pages.clients;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.ModelController;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientsPageController implements Initializable {
    @FXML private RadioButton naturalRadioButton;
    @FXML private RadioButton legalRadioButton;
    @FXML private ListView<NaturalPerson> naturalListView;
    @FXML private ListView<LegalPerson> legalListView;

    public void showNatural() {
        try {
            ObservableList<NaturalPerson> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getNaturalPersons());
            naturalListView.setItems(list);

            naturalListView.setVisible(true);
            naturalListView.setManaged(true);
            legalListView.setVisible(false);
            legalListView.setManaged(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showLegal() {
        try {
            ObservableList<LegalPerson> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getLegalPersons());
            legalListView.setItems(list);

            legalListView.setVisible(true);
            legalListView.setManaged(true);
            naturalListView.setVisible(false);
            naturalListView.setManaged(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        naturalRadioButton.setToggleGroup(group);
        legalRadioButton.setToggleGroup(group);

        naturalRadioButton.setSelected(true);
        showNatural();
    }
}
