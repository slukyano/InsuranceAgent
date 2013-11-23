package ui.pages.home;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import ui.UiRootController;
import ui.controls.AbstractForm;
import ui.controls.AbstractListView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentsListView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonListView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonView;
import ui.controls.clients.natural.NaturalPersonsListView;
import ui.controls.companies.CompaniesListView;
import ui.controls.companies.CompanyForm;
import ui.controls.insurances.InsuranceForm;
import ui.controls.insurances.InsurancesListView;
import ui.pages.EditPage;
import ui.pages.SelectPage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController {
    public void naturalsClick(ActionEvent actionEvent) throws IOException {
        try {
            final List<NaturalPerson> list = ModelController.getInstance().getNaturalPersons();

            SelectPage<NaturalPerson> page = new SelectPage<NaturalPerson>() {
                @Override
                protected AbstractListView<NaturalPerson> listViewFactory() {
                    return new NaturalPersonsListView(list);
                }
            };
            page.addSelectionListener(new SelectionListener<NaturalPerson>() {
                @Override
                public void objectSelected(SelectionProvider<NaturalPerson> provider,
                                           NaturalPerson selectedObject) {
                    UiRootController.getInstance().navigateForward(new NaturalPersonView(selectedObject),
                            selectedObject.getName());
                }
            });

            UiRootController.getInstance().navigateForward(page, "Natural persons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void legalsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().navigateForward(
                    new LegalPersonListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getLegalPersons())),
                    "Legal persons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agentsClick(ActionEvent actionEvent) throws IOException {
        try {
            UiRootController.getInstance().navigateForward(
                    new AgentsListView(
                            FXCollections.observableArrayList(ModelController.getInstance().getAgents())),
                    "Agents");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) throws IOException {
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
        EditPage<Agent> editPage = new EditPage<Agent>() {
            @Override
            protected AbstractForm<Agent> formFactory() {
                return new AgentForm();
            }
        };

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
        EditPage<Insurance> editPage = new EditPage<Insurance>() {
            @Override
            protected AbstractForm<Insurance> formFactory() {
                return new InsuranceForm();
            }
        };

        UiRootController.getInstance().navigateForward(editPage,
                "New insurance");
    }

    public void newNatural(ActionEvent actionEvent) {

        EditPage<NaturalPerson> editPage = new EditPage<NaturalPerson>() {
            @Override
            protected AbstractForm<NaturalPerson> formFactory() {
                return new NaturalPersonForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage,
                "New natural person");
    }

    public void newLegal(ActionEvent actionEvent) {
        EditPage<LegalPerson> editPage = new EditPage<LegalPerson>() {
            @Override
            protected AbstractForm<LegalPerson> formFactory() {
                return new LegalPersonForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage,
                "New legal person");
    }

    public void newCompany(ActionEvent actionEvent) {
        EditPage<Company> editPage = new EditPage<Company>() {
            @Override
            protected AbstractForm<Company> formFactory() {
                return new CompanyForm();
            }
        };
        UiRootController.getInstance().navigateForward(editPage,
                "New company");
    }
}
