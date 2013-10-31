package ui.clients;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.ModelController;
import model.clients.NaturalPerson;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: lsa
 * Date: 30.10.13
 */
public class ClientsPageController implements Initializable {
    public ListView<NaturalPerson> clientsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<NaturalPerson> list;
        try {
            list = FXCollections.observableArrayList(ModelController.getInstance().getNaturalPersons());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        clientsListView.setItems(list);

        clientsListView.setCellFactory(new Callback<ListView<NaturalPerson>, ListCell<NaturalPerson>>() {
            @Override
            public ListCell<NaturalPerson> call(ListView<NaturalPerson> listView) {
                return new NaturalPersonCell();
            }
        });
    }
}
