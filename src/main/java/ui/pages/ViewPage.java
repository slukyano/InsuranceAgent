package ui.pages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.UiRootController;
import ui.controls.AbstractView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

public abstract class ViewPage<T> extends AbstractView<T> {
    @FXML protected Button updateButton;
    @FXML protected Button deleteButton;

    public ViewPage() {
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditPage<T> editPage = editPageFactory(data);
                editPage.addSelectionListener(new SelectionListener<T>() {
                    @Override
                    public void objectSelected(SelectionProvider<T> provider, T selectedObject) {
                        setData(selectedObject);
                        UiRootController.getInstance().navigateBack();
                    }
                });

                UiRootController.getInstance().navigateForward(editPage, editPageTitle());
            }
        });
    }

    public ViewPage(T data) {
        this();
        setData(data);
    }

    protected abstract EditPage<T> editPageFactory(T dataToEdit);
    protected abstract String editPageTitle();
}
