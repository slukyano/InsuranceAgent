package ui.pages.clients;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import model.clients.Client;
import model.clients.LegalPerson;
import model.clients.NaturalPerson;
import ui.UiRootController;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;
import ui.controls.clients.legal.LegalPersonForm;
import ui.controls.clients.legal.LegalPersonListView;
import ui.controls.clients.natural.NaturalPersonForm;
import ui.controls.clients.natural.NaturalPersonsListView;
import ui.pages.EditPage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;

public class ClientsPage extends Pane implements SelectionProvider<Client> {
    @FXML private Button newClientButton;
    @FXML private RadioButton naturalRadioButton;
    @FXML private RadioButton legalRadioButton;
    @FXML private NaturalPersonsListView naturalListView;
    @FXML private LegalPersonListView legalListView;
    private HashSet<SelectionListener<Client>> listeners = new HashSet<SelectionListener<Client>>();

    @Override
    public void addSelectionListener(SelectionListener<Client> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSelectionListener(SelectionListener<Client> listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Client selectedObject) {
        for (SelectionListener<Client> listener : listeners)
            listener.objectSelected(this, selectedObject);
    }

    public ClientsPage(Collection<NaturalPerson> naturalPersons, Collection<LegalPerson> legalPersons) {
        URL fxmlUrl = getClass().getResource("/ui/pages/clients/ClientsPage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            return;
        }

        naturalListView.setItems(FXCollections.observableArrayList(naturalPersons));
        naturalListView.addSelectionListener(new SelectionListener<NaturalPerson>() {
            @Override
            public void objectSelected(SelectionProvider<NaturalPerson> provider,
                                       NaturalPerson selectedObject) {
                notifyListeners(selectedObject);
            }
        });

        legalListView.setItems(FXCollections.observableArrayList(legalPersons));
        legalListView.addSelectionListener(new SelectionListener<LegalPerson>() {
            @Override
            public void objectSelected(SelectionProvider<LegalPerson> provider,
                                       LegalPerson selectedObject) {
                notifyListeners(selectedObject);
            }
        });

        naturalRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showNatural();
            }
        });
        legalRadioButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showLegal();
            }
        });

        ToggleGroup group = new ToggleGroup();
        naturalRadioButton.setToggleGroup(group);
        legalRadioButton.setToggleGroup(group);

        newClientButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditPage editPage = naturalRadioButton.isSelected()
                        ? new EditPage<NaturalPerson>(new NaturalPersonForm())
                        : new EditPage<LegalPerson>(new LegalPersonForm());

                editPage.addSelectionListener(new SelectionListener() {
                    @Override
                    public void objectSelected(SelectionProvider provider, Object selectedObject) {
                        UiRootController.getInstance().navigateBack();
                        notifyListeners((Client) selectedObject);
                    }
                });

                UiRootController.getInstance().navigateForward(editPage,
                        "New client");
            }
        });

        naturalRadioButton.setSelected(true);
        showNatural();
    }

    public void showNatural() {
        naturalListView.setVisible(true);
        naturalListView.setManaged(true);
        legalListView.setVisible(false);
        legalListView.setManaged(false);
    }

    public void showLegal() {
        legalListView.setVisible(true);
        legalListView.setManaged(true);
        naturalListView.setVisible(false);
        naturalListView.setManaged(false);
    }
}
