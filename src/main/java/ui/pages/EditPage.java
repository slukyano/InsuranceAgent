package ui.pages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ui.UiRootController;
import ui.controls.AbstractForm;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

public abstract class EditPage<T> extends Pane implements SelectionProvider<T>, Initializable {
    @FXML protected Pane formContainer;
    @FXML protected Button submitButton;
    private final AbstractForm<T> form = formFactory();
    private final HashSet<SelectionListener<T>> listeners = new HashSet<SelectionListener<T>>();

    public EditPage() {
        URL fxmlUrl = getClass().getResource("/ui/pages/EditPage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public EditPage(T data) {
        this();
        form.setData(data);
    }

    protected abstract AbstractForm<T> formFactory();

    @Override
    public void addSelectionListener(SelectionListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeSelectionListener(SelectionListener<T> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(T selectedObject) {
        for (SelectionListener<T> listener : listeners)
            listener.objectSelected(this, selectedObject);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formContainer.getChildren().add(form);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    T object = form.commitObject();
                    notifyListeners(object);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                UiRootController.getInstance().navigateBack();
            }
        });
    }
}
