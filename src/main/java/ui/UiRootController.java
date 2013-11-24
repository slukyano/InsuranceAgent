package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.ModelController;
import model.UserType;
import model.clients.Client;
import ui.pages.clients.ClientPage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UiRootController implements Initializable {

    @FXML public BorderPane rootBorderPane;
    @FXML public VBox topBox;
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
            rootBorderPane.getStyleClass().add("my-gridpane");
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
        topBox.getChildren().get(0).setVisible(true);
        this.setViewingParent(rootView);
        NavigateBarController.getInstance().AddButton(pages.size()-1, pageTitle);
    }

    public void navigateBack(int index){
        if (index==0)
            topBox.getChildren().get(0).setVisible(false);
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
            Parent navigateBar =  FXMLLoader.load(getClass().getResource("/ui/NavigateBar.fxml"));
            topBox.getChildren().add(navigateBar);

            Parent messageBar =  FXMLLoader.load(getClass().getResource("/ui/MessageBar.fxml"));
            topBox.getChildren().add(messageBar);

            navigateBar.setVisible(false);

            Parent home;
            UserType currentUser = ModelController.getInstance().getUserType();
            if (currentUser == UserType.LEGAL || currentUser == UserType.NATURAL) {
                try {
                    home = new ClientPage((Client)ModelController.getInstance().getUserObject());
                } catch (SQLException e) {
                    e.printStackTrace();
                    MessageBarController.getInstance().showMessage("Error while loading user object");
                    return;
                }
            }
            else {
            String homePage = null;
                switch (ModelController.getInstance().getUserType()) {
                    case AGENT:
                        homePage = "/ui/pages/home/Home_Agent.fxml";
                        break;

                    case MANAGER:
                        homePage = "/ui/pages/home/Home_Manager.fxml";
                        break;

                    case ADMIN:
                        homePage = "/ui/pages/home/Home_Admin.fxml";
                        break;
                }
                home = FXMLLoader.load(getClass().getResource(homePage));
            }

            pages.add(home);
            rootBorderPane.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
