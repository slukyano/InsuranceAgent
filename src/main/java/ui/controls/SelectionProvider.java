package ui.controls;

public interface SelectionProvider<T> {
    void addSelectionListener(SelectionListener<T> listener);
    void removeSelectionListener(SelectionListener<T> listener);
}
