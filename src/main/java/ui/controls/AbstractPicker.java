package ui.controls;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractPicker<T> extends AbstractView<T> implements Initializable {
    @FXML protected TextField valueField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pickObject();
            }
        });
    }

    @Override
    protected final URL getFxmlUrl() {
        return getClass().getResource("/ui/controls/AbstractPicker.fxml");
    }

    @Override
    protected final void update() {
        valueField.setText(getValueString());
    }

    protected abstract String getValueString();

    public abstract void pickObject();
}
