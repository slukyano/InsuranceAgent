package ui.controls;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public abstract class AbstractListView<T> extends ListView<T> {
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
    }

    public AbstractListView(ObservableList<T> ts) {
        super(ts);
    }

    protected abstract Node cellGraphicFactory(T dataObject);
}
