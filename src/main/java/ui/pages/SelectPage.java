package ui.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ui.MessageBarController;
import ui.controls.AbstractListView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;

public class SelectPage<T> extends Pane implements SelectionProvider<T> {
    private HashSet<SelectionListener<T>> listeners = new HashSet<SelectionListener<T>>();
    private AbstractListView<T> listView;
    private Matcher<T> matcher;
    @FXML private TextField searchField;
    @FXML private Pane listContainer;
    private List<T> list;

    public SelectPage() {
        URL fxmlUrl = getClass().getResource("/ui/pages/SelectPage.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
            MessageBarController.getInstance().showMessage("Error while loading page");
        }

        searchField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<T> newItems = FXCollections.observableArrayList();
                for (T t : list)
                    if (matcher.match(searchField.getText(), t))
                        newItems.add(t);
                listView.setItems(newItems);
            }
        });
    }

    public SelectPage(AbstractListView<T> listView) {
        this();
        setListView(listView);
    }

    public AbstractListView<T> getListView() {
        return listView;
    }

    public void setListView(AbstractListView<T> listView) {
        this.listView = listView;
        listContainer.getChildren().clear();
        listContainer.getChildren().add(listView);
        listView.addSelectionListener(new SelectionListener<T>() {
            @Override
            public void objectSelected(SelectionProvider<T> provider, T selectedObject) {
                notifyListeners(selectedObject);
            }
        });
        list = listView.getItems();
    }

    private void notifyListeners(T selectedObject) {
        for (SelectionListener<T> listener : listeners)
            listener.objectSelected(this, selectedObject);
    }

    @Override
    public final void addSelectionListener(SelectionListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public final void removeSelectionListener(SelectionListener<T> listener) {
        listeners.remove(listener);
    }

    public Matcher<T> getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher<T> matcher) {
        this.matcher = matcher;
    }
}
