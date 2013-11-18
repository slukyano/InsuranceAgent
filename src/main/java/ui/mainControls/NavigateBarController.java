package ui.mainControls;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import ui.UiRootController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: nik
 * Date: 19.11.13
 * Time: 2:01
 * To change this template use File | Settings | File Templates.
 */
public class NavigateBarController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void backClick(ActionEvent actionEvent) {
        UiRootController uiRootController = UiRootController.getInstance();
        uiRootController.NavigateBack(uiRootController.ViewsCount()-2);
    }

    public void homeClick(ActionEvent actionEvent) {
        UiRootController.getInstance().NavigateBack(0);
    }
}
