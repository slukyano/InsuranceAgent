package ui.pages.home;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import model.Agent;
import model.Company;
import model.ModelController;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import model.insurances.Insurance;
import model.insurances.InsuranceType;
import ui.MessageBarController;
import ui.UiRootController;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.agents.AgentForm;
import ui.controls.agents.AgentsListView;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.companies.CompaniesListView;
import ui.controls.companies.CompanyForm;
import ui.controls.insurances.InsuranceForm;
import ui.controls.insurances.InsurancesListView;
import ui.controls.insurances.insurancetypes.InsuranceTypeForm;
import ui.pages.EditPage;
import ui.pages.Matcher;
import ui.pages.SelectPage;
import ui.pages.agents.AgentPage;
import ui.pages.clients.ClientPage;
import ui.pages.clients.ClientsPage;
import ui.pages.company.CompanyPage;
import ui.pages.insurances.InsurancePage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController {
    public void clientsClick(ActionEvent actionEvent) {
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getUserType().getNaturalPersons();
            List<LegalPerson>  legals = ModelController.getInstance().getUserType().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);

            page.addSelectionListener(new SelectionListener<Client>() {
                @Override
                public void objectSelected(SelectionProvider<Client> provider,
                                           Client selectedObject) {
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

            SelectPage<Agent> page = new SelectPage<Agent>(listView);
            page.setMatcher(new Matcher<Agent>() {
                @Override
                public boolean match(String pattern, Agent object) {
                    return object.getFullName().contains(pattern);
                }
            });
            page.addSelectionListener(new SelectionListener<Agent>() {
                @Override
                public void objectSelected(SelectionProvider<Agent> provider, Agent selectedObject) {
                    UiRootController.getInstance().navigateForward(new AgentPage(selectedObject),
                            selectedObject.getFullName());
                }
            });
            UiRootController.getInstance().navigateForward(page, "Agent select");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void companiesClick(ActionEvent actionEvent) {
        try {
            CompaniesListView listView = new CompaniesListView();
            List<Company> list = ModelController.getInstance().getCompanies();
            listView.setItems(FXCollections.observableArrayList(list));
            SelectPage<Company> page = new SelectPage<Company>(listView);
            page.setMatcher(new Matcher<Company>() {
                @Override
                public boolean match(String pattern, Company object) {
                    return object.getName().contains(pattern);
                }
            });
            page.addSelectionListener(new SelectionListener<Company>() {
                @Override
                public void objectSelected(SelectionProvider<Company> provider, Company selectedObject) {
                    UiRootController.getInstance().navigateForward(new CompanyPage(selectedObject),
                            selectedObject.getName());
                }
            });
            UiRootController.getInstance().navigateForward(page, "Companies");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insurancesClick(ActionEvent actionEvent) {
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list = ModelController.getInstance().getUserType().getInsurances();
            listView.setItems(FXCollections.observableArrayList(list));
            SelectPage<Insurance> page = new SelectPage<Insurance>(listView);
            page.setMatcher(new Matcher<Insurance>() {
                @Override
                public boolean match(String pattern, Insurance object) {
                    return object.getInsuranceTypeName().contains(pattern)
                            || object.getClientName().contains(pattern)
                            || object.getAgentName().contains(pattern)
                            || object.getCompanyName().contains(pattern);
                }
            });
            page.addSelectionListener(new SelectionListener<Insurance>() {
                @Override
                public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                    UiRootController.getInstance().navigateForward(new InsurancePage(selectedObject),
                            selectedObject.getInsuranceTypeName());
                }
            });
            UiRootController.getInstance().navigateForward(page, "Insurances");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void allClientsClick(ActionEvent actionEvent) {
        try {
            List<NaturalPerson> naturals = ModelController.getInstance().getNaturalPersons();
            List<LegalPerson>  legals = ModelController.getInstance().getLegalPersons();
            ClientsPage page = new ClientsPage(naturals, legals);

            page.addSelectionListener(new SelectionListener<Client>() {
                @Override
                public void objectSelected(SelectionProvider<Client> provider,
                                           Client selectedObject) {
                    UiRootController.getInstance().navigateForward(new ClientPage(selectedObject),
                            selectedObject.getName());
                }
            });

            UiRootController.getInstance().navigateForward(page, "Clients");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void allInsurancesClick(ActionEvent actionEvent) {
        try {
            InsurancesListView listView = new InsurancesListView();
            List<Insurance> list = ModelController.getInstance().getInsurances();
            listView.setItems(FXCollections.observableArrayList(list));
            SelectPage<Insurance> page = new SelectPage<Insurance>(listView);
            page.setMatcher(new Matcher<Insurance>() {
                @Override
                public boolean match(String pattern, Insurance object) {
                    return object.getInsuranceTypeName().contains(pattern)
                            || object.getClientName().contains(pattern)
                            || object.getAgentName().contains(pattern)
                            || object.getCompanyName().contains(pattern);
                }
            });
            page.addSelectionListener(new SelectionListener<Insurance>() {
                @Override
                public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                    UiRootController.getInstance().navigateForward(new InsurancePage(selectedObject),
                            selectedObject.getInsuranceTypeName());
                }
            });
            UiRootController.getInstance().navigateForward(page, "Insurances");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void newInsuranceClick(ActionEvent actionEvent) {
        EditPage<Insurance> editPage = new EditPage<Insurance>(new InsuranceForm());
        editPage.addSelectionListener(new SelectionListener<Insurance>() {
            @Override
            public void objectSelected(SelectionProvider<Insurance> provider, Insurance selectedObject) {
                UiRootController.getInstance().navigateBack();
                UiRootController.getInstance().navigateForward(new InsurancePage(selectedObject),
                        selectedObject.getInsuranceTypeName());
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New insurance");
    }

    public void newNatural(ActionEvent actionEvent) {
        EditPage<NaturalPerson> editPage = new EditPage<NaturalPerson>(new NaturalPersonForm());
        editPage.addSelectionListener(new SelectionListener<NaturalPerson>() {
            @Override
            public void objectSelected(SelectionProvider<NaturalPerson> provider, NaturalPerson selectedObject) {
                UiRootController.getInstance().navigateBack();
                UiRootController.getInstance().navigateForward(new ClientPage(selectedObject),
                        selectedObject.getName());
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New natural person");
    }

    public void newLegal(ActionEvent actionEvent) {
        EditPage<LegalPerson> editPage = new EditPage<LegalPerson>(new LegalPersonForm());
        editPage.addSelectionListener(new SelectionListener<LegalPerson>() {
            @Override
            public void objectSelected(SelectionProvider<LegalPerson> provider, LegalPerson selectedObject) {
                UiRootController.getInstance().navigateBack();
                UiRootController.getInstance().navigateForward(new ClientPage(selectedObject),
                        selectedObject.getName());
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New legal person");
    }

    public void newCompany(ActionEvent actionEvent) {
        EditPage<Company> editPage = new EditPage<Company>(new CompanyForm());
        editPage.addSelectionListener(new SelectionListener<Company>() {
            @Override
            public void objectSelected(SelectionProvider<Company> provider, Company selectedObject) {
                UiRootController.getInstance().navigateBack();
                UiRootController.getInstance().navigateForward(new CompanyPage(selectedObject),
                        selectedObject.getName());
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New company");
    }

    public void newAgentClick(ActionEvent actionEvent) {
        EditPage<Agent> editPage = new EditPage<Agent>(new AgentForm());
        editPage.addSelectionListener(new SelectionListener<Agent>() {
            @Override
            public void objectSelected(SelectionProvider<Agent> provider, Agent selectedObject) {
                UiRootController.getInstance().navigateBack();
                UiRootController.getInstance().navigateForward(new AgentPage(selectedObject),
                        selectedObject.getFullName());
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New agent");
    }

    public void newInsuranceTypeClick(ActionEvent actionEvent) {
        EditPage<InsuranceType> editPage = new EditPage<InsuranceType>(new InsuranceTypeForm());
        editPage.addSelectionListener(new SelectionListener<InsuranceType>() {
            @Override
            public void objectSelected(SelectionProvider<InsuranceType> provider, InsuranceType selectedObject) {
                UiRootController.getInstance().navigateBack();
            }
        });

        UiRootController.getInstance().navigateForward(editPage,
                "New insurance type");
    }

    public void newAdminClick(ActionEvent actionEvent) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/ui/pages/util/NewAdminPage.fxml"));
            UiRootController.getInstance().navigateForward(page, "New admin");
        } catch (IOException e) {
            e.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while loading page");
        }
    }

    public void changePasswordClick(ActionEvent actionEvent) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/ui/pages/util/ChangePasswordPage.fxml"));
            UiRootController.getInstance().navigateForward(page, "Change password");
        } catch (IOException e) {
            e.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while loading page");
        }
    }

    public void logOutClick(ActionEvent actionEvent) {
        UiRootController.getInstance().presentLoginPage();
    }
}
