package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: nik
 * Date: 19.11.13
 * Time: 2:01
 * To change this template use File | Settings | File Templates.
 */
public class NavigateBarController implements Initializable {

    @FXML public FlowPane breadCrumbsPane;
    static NavigateBarController instance;
    ArrayList<Button> breadCrumbsButtons = new ArrayList<Button>();

    //TODO should return a controller, but it returns null
    static public NavigateBarController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance=this;
        breadCrumbsPane.getStyleClass().add("breadcrumbs");
        //breadCrumbsPane.

        AddButton(0,"Home");
        //breadCrumbsPane.getChildren().add(new Button());
        //breadCrumbsPane.getChildren().add(new Button());
    }

    public void backClick(ActionEvent actionEvent) {
        breadCrumbsPane.getChildren().remove(breadCrumbsPane.getChildren().size()-1);
        UiRootController uiRootController = UiRootController.getInstance();
        uiRootController.NavigateBack(uiRootController.ViewsCount()-2);
    }

    public void AddButton(final int index, String text){
        Button button = new Button(text);
        if(index == 0)
            button.getStyleClass().addAll("item","first");
        else if (index > 1){
            Button previousButton =(Button)breadCrumbsPane.getChildren().get(breadCrumbsPane.getChildren().size()-1);
            previousButton.getStyleClass().remove("last");
            previousButton.getStyleClass().add("middle");
            button.getStyleClass().addAll("item", "last");
        }
        else if(index==1){
            button.getStyleClass().addAll("item", "last");
        }
        //button.setTextFill(Color.TRANSPARENT);
        breadCrumbsPane.getChildren().add(button);
        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(final ActionEvent event) {
                        for (int i= breadCrumbsPane.getChildren().size()-1; i>=index+1 ;i--) {
                            breadCrumbsPane.getChildren().remove(i);
                        }
                        UiRootController.getInstance().NavigateBack(index);
                    }
                });
    }

    public void homeClick(ActionEvent actionEvent) {
        UiRootController.getInstance().NavigateBack(0);
    }

}
