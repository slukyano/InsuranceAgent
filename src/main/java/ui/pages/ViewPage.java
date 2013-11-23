package ui.pages;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ui.UiRootController;
import ui.controls.AbstractView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

public abstract class ViewPage<T> extends AbstractView<T> {
    @FXML protected Pane viewContainer;
    protected AbstractView<T> objectView;
    @FXML protected Button updateButton;

    private ViewPage(AbstractView<T> objectView) {
        this.objectView = objectView;

        viewContainer.getChildren().add(objectView);
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

    public ViewPage(AbstractView<T> objectView, T data) {
        this(objectView);
        setData(data);
    }

    protected abstract EditPage<T> editPageFactory(T dataToEdit);
    protected abstract String editPageTitle();

    @Override
    protected void update() {
        objectView.setData(data);
    }
}
