package ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UiRootController implements Initializable {
    public TabPane rootTabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Parent clientsRoot = FXMLLoader.load(getClass().getResource("/ui/clients/ClientsPage.fxml"));
            Tab loginTab = new Tab("Clients");
            loginTab.setContent(clientsRoot);
            rootTabPane.getTabs().add(loginTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
