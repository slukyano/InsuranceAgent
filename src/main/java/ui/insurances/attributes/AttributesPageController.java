package ui.insurances.attributes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.ModelController;
import model.insurances.attributes.InsuranceAttribute;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AttributesPageController implements Initializable {
    @FXML public ListView<InsuranceAttribute> attributesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        attributesListView.setCellFactory(
                new Callback<ListView<InsuranceAttribute>, ListCell<InsuranceAttribute>>() {
                    @Override
                    public ListCell<InsuranceAttribute> call(ListView listView) {
                        return new AttributeCell();
                    }
                });

        try {
            ObservableList<InsuranceAttribute> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getInsuranceAttributes());
            attributesListView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
