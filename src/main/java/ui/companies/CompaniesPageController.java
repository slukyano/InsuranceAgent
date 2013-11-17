package ui.companies;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Company;
import model.ModelController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompaniesPageController implements Initializable {
    public ListView<Company> companiesListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        companiesListView.setCellFactory(new Callback<ListView<Company>, ListCell<Company>>() {
            @Override
            public ListCell<Company> call(ListView listView) {
                return new CompanyCell();
            }
        });

        try {
            ObservableList<Company> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getCompanies());
            companiesListView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
