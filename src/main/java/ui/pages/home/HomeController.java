package ui.pages.home;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.ModelController;
import ui.UiRootController;
import ui.controls.agents.AgentsListView;
import ui.controls.clients.legal.LegalPersonListView;
import ui.controls.clients.natural.NaturalPersonsListView;
import ui.controls.companies.CompaniesListView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class HomeController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void naturalsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().NavigateForward(
                    new NaturalPersonsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getNaturalPersons())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void legalsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().NavigateForward(
                    new LegalPersonListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getLegalPersons())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agentsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().NavigateForward(
                    new AgentsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getAgents())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().NavigateForward(
                    new CompaniesListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getCompanies())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
