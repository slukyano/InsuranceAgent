package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import model.ModelController;

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
    private int viewsCount(){
        return pages.size();
    }

    public void navigateForward(Parent rootView, String pageTitle){
        pages.add(rootView);
        rootBorderPane.getTop().setVisible(true);
        this.setViewingParent(rootView);
        NavigateBarController.getInstance().AddButton(pages.size()-1, pageTitle);
    }

    public void navigateBack(int index){
        if (index==0)
            rootBorderPane.getTop().setVisible(false);
        for (int i= pages.size()-1; i>=index+1 ;i--) {
            pages.remove(i);
        }
        this.setViewingParent(pages.get(index));
        NavigateBarController.getInstance().removeButtons(index);
    }

    public void navigateBack() {
        navigateBack(viewsCount() - 2);
    }

    public void PresentHomeView(){
        try {
            String homePage = null;

            switch (ModelController.getInstance().getUserType()) {
                case NATURAL:
                case LEGAL:
                    homePage = "Home_Client.fxml";
                    break;

                case AGENT:
                    homePage = "Home_Agent.fxml";
                    break;

                case MANAGER:
                    homePage = "Home_Manager.fxml";
                    break;

                case ADMIN:
                    homePage = "Home_Admin.fxml";
                    break;

                default:
                    //throw exception?
                    break;
            }

            Parent home = FXMLLoader.load(getClass().getResource("/ui/pages/home/" + homePage));
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
