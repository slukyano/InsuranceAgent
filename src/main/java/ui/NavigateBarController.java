package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    public void setHomeButtonText(String text) {
        ((Button)breadCrumbsPane.getChildren().get(0)).setText(text);
    }

    public void backClick(ActionEvent actionEvent) {
        breadCrumbsPane.getChildren().remove(breadCrumbsPane.getChildren().size()-1);
        UiRootController uiRootController = UiRootController.getInstance();
        uiRootController.navigateBack();
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
                        UiRootController.getInstance().navigateBack(index);
                    }
                });
    }

    public void homeClick(ActionEvent actionEvent) {
        UiRootController.getInstance().navigateBack(0);
    }

    public void removeButtons(int index) {
        for (int i= breadCrumbsPane.getChildren().size()-1; i>=index+1 ;i--) {
            breadCrumbsPane.getChildren().remove(i);
        }
        if(breadCrumbsPane.getChildren().size()!=1) {
            ((Button)breadCrumbsPane.getChildren().get(breadCrumbsPane.getChildren().size()-1)).getStyleClass().add("last");
        }

    }
}
