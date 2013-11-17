package ui.insurances.types;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.ModelController;
import model.insurances.InsuranceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsuranceTypesPageController implements Initializable {
    public ListView<InsuranceType> typesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typesListView.setCellFactory(new Callback<ListView<InsuranceType>, ListCell<InsuranceType>>() {
            @Override
            public ListCell<InsuranceType> call(ListView listView) {
                return new InsuranceTypeCell();
            }
        });

        try {
            ObservableList<InsuranceType> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getInsuranceTypes());
            typesListView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
