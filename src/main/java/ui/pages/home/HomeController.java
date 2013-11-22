package ui.pages.home;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.UiRootController;
import ui.controls.AbstractForm;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentsListView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonListView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonsListView;
import ui.controls.companies.CompaniesListView;
import ui.controls.companies.CompanyForm;
import ui.controls.insurances.InsuranceForm;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;

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
            UiRootController.getInstance().navigateForward(
                    new NaturalPersonsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getNaturalPersons())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void legalsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().navigateForward(
                    new LegalPersonListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getLegalPersons())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agentsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().navigateForward(
                    new AgentsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getAgents())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().navigateForward(
                    new CompaniesListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getCompanies())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newAgentClick(ActionEvent actionEvent) {
        EditPage<Agent> editPage = new EditPage<Agent>() {
            @Override
            protected AbstractForm<Agent> formFactory() {
                return new AgentForm();
            }
        };

        UiRootController.getInstance().navigateForward(editPage);
    }

    public void insurancesClick(ActionEvent actionEvent) {
        try {
            UiRootController.getInstance().navigateForward(
                    new InsurancesListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getInsurances())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newInsuranceClick(ActionEvent actionEvent) {
        EditPage<Insurance> editPage = new EditPage<Insurance>() {
            @Override
            protected AbstractForm<Insurance> formFactory() {
                return new InsuranceForm();
            }
        };

        UiRootController.getInstance().navigateForward(editPage);
    }

    public void newNatural(ActionEvent actionEvent) {

        EditPage<NaturalPerson> editPage = new EditPage<NaturalPerson>() {
            @Override
            protected AbstractForm<NaturalPerson> formFactory() {
                return new NaturalPersonForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage);
    }

    public void newLegal(ActionEvent actionEvent) {
        EditPage<LegalPerson> editPage = new EditPage<LegalPerson>() {
            @Override
            protected AbstractForm<LegalPerson> formFactory() {
                return new LegalPersonForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage);
    }

    public void newCompany(ActionEvent actionEvent) {
        EditPage<Company> editPage = new EditPage<Company>() {
            @Override
            protected AbstractForm<Company> formFactory() {
                return new CompanyForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage);
    }
}
