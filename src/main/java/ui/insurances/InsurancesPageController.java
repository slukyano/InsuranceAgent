package ui.insurances;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.ModelController;
import model.insurances.Insurance;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsurancesPageController implements Initializable {
    @FXML public ListView<Insurance> insurancesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insurancesListView.setCellFactory(new Callback<ListView<Insurance>, ListCell<Insurance>>() {
            @Override
            public ListCell<Insurance> call(ListView listView) {
                return new InsuranceCell();
            }
        });

        try {
            ObservableList<Insurance> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getInsurances());
            insurancesListView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
