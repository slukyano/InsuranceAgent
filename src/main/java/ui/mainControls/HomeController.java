package ui.mainControls;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import ui.UiRootController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: nik
 * Date: 19.11.13
 * Time: 1:43
 * To change this template use File | Settings | File Templates.
 */
public class HomeController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void naturalsClick(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("/ui/clients/ClientsPage.fxml"));
        UiRootController.getInstance().NavigateForward(view);
    }
    public void agentsClick(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("/ui/agents/AgentsPage.fxml"));
        UiRootController.getInstance().NavigateForward(view);
    }

    public void legalsClick(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("/ui/clients/ClientsPage.fxml"));
        UiRootController.getInstance().NavigateForward(view);
    }

    public void companiesClick(ActionEvent actionEvent) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource("/ui/companies/CompaniesPage.fxml"));
        UiRootController.getInstance().NavigateForward(view);

    }
}
