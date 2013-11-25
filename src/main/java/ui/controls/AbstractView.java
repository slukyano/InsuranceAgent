package ui.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import ui.MessageBarController;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractView<T> extends StackPane {
    protected T data;

    public AbstractView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getFxmlUrl());
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while loading user control");
        }
    }

    public AbstractView(T data) {
        this();
        setData(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        update();
    }

    protected abstract URL getFxmlUrl();

    protected abstract void update();
}
