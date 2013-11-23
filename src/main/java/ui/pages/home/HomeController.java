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
import ui.pages.agents.AgentPage;
import ui.pages.clients.ClientPage;
import ui.pages.clients.ClientsPage;
import ui.pages.company.CompanyPage;
import ui.pages.insurances.InsurancePage;

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
                    UiRootController.getInstance().navigateForward(new ClientPage(selectedObject),
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
            AgentsListView listView = new AgentsListView();
            List<Agent> list = ModelController.getInstance().getAgents();
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(new SelectionListener<Agent>() {
                @Override
                public void objectSelected(SelectionProvider<Agent> provider, Agent selectedObject) {
                    UiRootController.getInstance().navigateForward(new AgentPage(selectedObject),
                            selectedObject.getFullName());
                }
            });
            UiRootController.getInstance().navigateForward(listView, "Agent select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) {
        try {
            CompaniesListView listView = new CompaniesListView();
            List<Company> list = ModelController.getInstance().getCompanies();
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(new SelectionListener<Company>() {
                @Override
                public void objectSelected(SelectionProvider<Company> provider, Company selectedObject) {
                    UiRootController.getInstance().navigateForward(new CompanyPage(selectedObject),
                            selectedObject.getName());
                }
            });
            UiRootController.getInstance().navigateForward(listView, "Agent select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insurancesClick(ActionEvent actionEvent) {
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list = ModelController.getInstance().getInsurances();
            listView.setItems(FXCollections.observableArrayList(list));
            listView.addSelectionListener(new SelectionListener<Insurance>() {
                @Override
                public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                    UiRootController.getInstance().navigateForward(new InsurancePage(selectedObject),
                            selectedObject.getInsuranceTypeName());
                }
            });
            UiRootController.getInstance().navigateForward(listView, "Insurances");
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

    public void newAgentClick(ActionEvent actionEvent) {
        EditPage<Agent> editPage = new EditPage<Agent>(new AgentForm());

        UiRootController.getInstance().navigateForward(editPage,
                "New agent");
    }
}
