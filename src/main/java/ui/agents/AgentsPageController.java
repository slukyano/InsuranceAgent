package ui.agents;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.Agent;
import model.ModelController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgentsPageController implements Initializable {
    public ListView<Agent> agentsListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        agentsListView.setCellFactory(new Callback<ListView<Agent>, ListCell<Agent>>() {
            @Override
            public ListCell<Agent> call(ListView listView) {
                return new AgentCell();
            }
        });

        try {
            ObservableList<Agent> list = FXCollections.observableArrayList(
                    ModelController.getInstance().getAgents());
            agentsListView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
