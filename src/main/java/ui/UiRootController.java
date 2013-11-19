package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UiRootController implements Initializable {

    @FXML public BorderPane rootTabPane;
    private ArrayList<Parent> pages = new ArrayList<Parent>();
    private static UiRootController instance;

    public static UiRootController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            instance=this;
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent parent = fxmlLoader.load(getClass().getResource("/ui/login/LoginPage.fxml"));

        rootTabPane.setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setViewingParent(Parent parent){
        rootTabPane.setCenter(parent);
    }
    public int ViewsCount(){
        return pages.size();
    }

    public void NavigateForward(Parent rootView){
        pages.add(rootView);
        rootTabPane.getTop().setVisible(true);
        this.setViewingParent(rootView);
    }

    public void NavigateBack (int index){
        if (index==0)
            rootTabPane.getTop().setVisible(false);
        for (int i= pages.size()-1; i>=index+1 ;i--) {
            pages.remove(i);
        }
        this.setViewingParent(pages.get(index));
    }

    public void PresentHomeView(){
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/ui/main/Home.fxml"));
            Parent navigateBar =  FXMLLoader.load(getClass().getResource("/ui/main/NavigateBar.fxml"));
            pages.add(home);
            rootTabPane.setTop(navigateBar);
            rootTabPane.getTop().setVisible(false);
            rootTabPane.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
