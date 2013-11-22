package ui.controls;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractListView<T> extends ListView<T> implements SelectionProvider<T> {
    private final List<SelectionListener<T>> listeners = new LinkedList<SelectionListener<T>>();

    public AbstractListView()
    {
        setCellFactory(
                new Callback<ListView<T>, ListCell<T>>() {
                    @Override
                    public ListCell<T> call(ListView listView) {
                        return new ListCell<T>() {
                            @Override
                            protected void updateItem(T dataObject, boolean b) {
                                super.updateItem(dataObject, b);
                                if (dataObject != null)
                                    setGraphic(cellGraphicFactory(dataObject));
                            }
                        };
                    }
                });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                T selectedItem = getSelectionModel().getSelectedItem();
                notifyListeners(selectedItem);
            }
        });
    }

    public AbstractListView(ObservableList<T> ts) {
        super();
    }

    public void addSelectionListener(SelectionListener<T> listener) {
        listeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener<T> listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(T selectedItem) {
        for (SelectionListener<T> listener : listeners)
            listener.objectSelected(this, selectedItem);
    }

    protected abstract Node cellGraphicFactory(T dataObject);
}
