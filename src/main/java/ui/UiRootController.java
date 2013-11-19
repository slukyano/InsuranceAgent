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

    @FXML public BorderPane rootBorderPane;
    private ArrayList<Parent> pages = new ArrayList<Parent>();
    private static UiRootController instance;

    public static UiRootController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            instance=this;
            Parent parent = FXMLLoader.load(getClass().getResource("/ui/pages/login/LoginPage.fxml"));

            rootBorderPane.setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setViewingParent(Parent parent){
        rootBorderPane.setCenter(parent);
    }
    public int ViewsCount(){
        return pages.size();
    }

    public void NavigateForward(Parent rootView){
        pages.add(rootView);
        rootBorderPane.getTop().setVisible(true);
        this.setViewingParent(rootView);
    }

    public void NavigateBack (int index){
        if (index==0)
            rootBorderPane.getTop().setVisible(false);
        for (int i= pages.size()-1; i>=index+1 ;i--) {
            pages.remove(i);
        }
        this.setViewingParent(pages.get(index));
    }

    public void PresentHomeView(){
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/ui/pages/home/Home.fxml"));
            Parent navigateBar =  FXMLLoader.load(getClass().getResource("/ui/NavigateBar.fxml"));
            pages.add(home);
            rootBorderPane.setTop(navigateBar);
            rootBorderPane.getTop().setVisible(false);
            rootBorderPane.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
