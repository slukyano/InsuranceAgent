package ui.controls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractView<T> extends Pane {
    protected T data;

    public AbstractView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getFxmlUrl());
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
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
