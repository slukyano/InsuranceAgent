package ui.pages.home;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.UiRootController;
import ui.controls.AbstractView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentsListView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonView;
import ui.controls.companies.CompaniesListView;
import ui.controls.companies.CompanyForm;
import ui.controls.insurances.InsuranceForm;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.clients.ClientPage;
import ui.pages.clients.ClientsPage;

import java.sql.SQLException;
import java.util.List;

public class HomeController {
    public void clientsClick(ActionEvent actionEvent) {
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getNaturalPersons();
            List<LegalPerson>  legals = ModelController.getInstance().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);

            page.addSelectionListener(new SelectionListener<Client>() {
                @Override
                public void objectSelected(SelectionProvider<Client> provider,
                                           Client selectedObject) {
                    AbstractView view = selectedObject.getClientType() == "NATURAL"
                            ? new NaturalPersonView()
                            : new LegalPersonView();
                    UiRootController.getInstance().navigateForward(new ClientPage(view, selectedObject),
                            selectedObject.getName());
                }
            });

            UiRootController.getInstance().navigateForward(page, "Clients");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agentsClick(ActionEvent actionEvent) {
        try {
            UiRootController.getInstance().navigateForward(
                    new AgentsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getAgents())),
                    "Agents");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) {
        try {
            UiRootController.getInstance().navigateForward(
                    new CompaniesListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getCompanies())),
                    "Companies");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newAgentClick(ActionEvent actionEvent) {
        EditPage<Agent> editPage = new EditPage<Agent>(new AgentForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New agent");
    }

    public void insurancesClick(ActionEvent actionEvent) {
        try {
            UiRootController.getInstance().navigateForward(
                    new InsurancesListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getInsurances())),
                    "Insurances");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newInsuranceClick(ActionEvent actionEvent) {
        EditPage<Insurance> editPage = new EditPage<Insurance>(new InsuranceForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New insurance");
    }

    public void newNatural(ActionEvent actionEvent) {
        EditPage<NaturalPerson> editPage = new EditPage<NaturalPerson>(new NaturalPersonForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New natural person");
    }

    public void newLegal(ActionEvent actionEvent) {
        EditPage<LegalPerson> editPage = new EditPage<LegalPerson>(new LegalPersonForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New legal person");
    }

    public void newCompany(ActionEvent actionEvent) {
        EditPage<Company> editPage = new EditPage<Company>(new CompanyForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New company");
    }
}
