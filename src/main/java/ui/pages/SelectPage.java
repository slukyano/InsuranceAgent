package ui.pages;

import javafx.scene.layout.Pane;
import ui.controls.AbstractListView;
import ui.controls.SelectionListener;
import ui.controls.SelectionProvider;

import java.util.HashSet;

public class SelectPage<T> extends Pane implements SelectionProvider<T> {
    private HashSet<SelectionListener<T>> listeners = new HashSet<SelectionListener<T>>();

    public SelectPage(AbstractListView<T> listView) {
        getChildren().add(listView);
        listView.addSelectionListener(new SelectionListener<T>() {
            @Override
            public void objectSelected(SelectionProvider<T> provider, T selectedObject) {
                notifyListeners(selectedObject);
            }
        });
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
}
