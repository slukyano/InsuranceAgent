package ui.controls;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractReferenceView<T> extends AbstractView<T> implements Initializable {
    @FXML protected Label valueText;

    protected abstract void onMouseClicked();

    protected AbstractReferenceView() {
    }

    protected AbstractReferenceView(T data) {
        super(data);
    }

    protected abstract String getValueString();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        valueText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onMouseClicked();
            }
        });
    }

    @Override
    protected final URL getFxmlUrl() {
        return getClass().getResource("/ui/controls/AbstractReferenceView.fxml");
    }

    @Override
    protected void update() {
        valueText.setText(getValueString());
    }
}
