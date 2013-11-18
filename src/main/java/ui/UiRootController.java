package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import ui.agents.AgentsPageController;
import ui.clients.ClientsPageController;
import ui.companies.CompaniesPageController;
import ui.insurances.InsurancesPageController;
import ui.insurances.attributes.AttributesPageController;
import ui.insurances.types.InsuranceTypesPageController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UiRootController implements Initializable {

    @FXML public BorderPane rootTabPane;
    private ArrayList<Parent> rootsArray = new ArrayList<Parent>();
    private static UiRootController instance;

    public static UiRootController getInstance() {
        return instance;
    }

    //private UiRootController uiRootController;

//    private static void addTab(TabPane tabPane, String fxmlPath, String tabCaption,
//                               Callback<Class<?>, Object> controllerFactory) throws IOException {
//        FXMLLoader loader = new FXMLLoader(UiRootController.class.getResource(fxmlPath));
//        loader.setControllerFactory(controllerFactory);
//        Parent root = (Parent)loader.load();
//        Tab tab = new Tab(tabCaption);
//        tab.setContent(root);
//        tabPane.getTabs().add(tab);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            instance=this;
        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent parent = fxmlLoader.load(getClass().getResource("/ui/login/LoginPage.fxml"));

        rootTabPane.setCenter(parent);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public void setViewingParent(Parent parent){
        rootTabPane.setCenter(parent);
    }
    public int ViewsCount(){
        return rootsArray.size();
    }

    public void NavigateForward(Parent rootView){
        rootsArray.add(rootView);
        this.setViewingParent(rootView);
    }

    public void NavigateBack (int index){
        for (int i=rootsArray.size()-1; i>=index+1 ;i--) {
            rootsArray.remove(i);
        }
        this.setViewingParent(rootsArray.get(index));
    }

    public void PresentHomeView(){
        try {
            Parent home = FXMLLoader.load(getClass().getResource("/ui/mainControls/Home.fxml"));
            Parent navigateBar =  FXMLLoader.load(getClass().getResource("/ui/mainControls/NavigateBar.fxml"));
            rootsArray.add(home);
            rootTabPane.setTop(navigateBar);
            rootTabPane.setCenter(home);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
