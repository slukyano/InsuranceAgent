package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.Callback;
import ui.agents.AgentsPageController;
import ui.clients.ClientsPageController;
import ui.companies.CompaniesPageController;
import ui.insurances.InsurancesPageController;
import ui.insurances.attributes.AttributesPageController;
import ui.insurances.types.InsuranceTypesPageController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UiRootController implements Initializable {
    @FXML public TabPane rootTabPane;

    private static void addTab(TabPane tabPane, String fxmlPath, String tabCaption,
                               Callback<Class<?>, Object> controllerFactory) throws IOException {
        FXMLLoader loader = new FXMLLoader(UiRootController.class.getResource(fxmlPath));
        loader.setControllerFactory(controllerFactory);
        Parent root = (Parent)loader.load();
        Tab tab = new Tab(tabCaption);
        tab.setContent(root);
        tabPane.getTabs().add(tab);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addTab(rootTabPane, "/ui/clients/ClientsPage.fxml", "Clients",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new ClientsPageController();
                        }
                    });

            addTab(rootTabPane, "/ui/agents/AgentsPage.fxml", "Agents",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new AgentsPageController();
                        }
                    });

            addTab(rootTabPane, "/ui/insurances/InsurancesPage.fxml", "Insurances",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new InsurancesPageController();
                        }
                    });

            addTab(rootTabPane, "/ui/insurances/attributes/AttributesPage.fxml", "Attributes",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new AttributesPageController();
                        }
                    });

            addTab(rootTabPane, "/ui/insurances/types/InsuranceTypesPage.fxml", "Insurance types",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new InsuranceTypesPageController();
                        }
                    });

            addTab(rootTabPane, "/ui/companies/CompaniesPage.fxml", "Companies",
                    new Callback<Class<?>, Object>() {
                        @Override
                        public Object call(Class<?> aClass) {
                            return new CompaniesPageController();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
