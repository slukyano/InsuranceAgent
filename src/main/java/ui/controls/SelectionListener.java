package ui.controls;

public interface SelectionListener<T> {
    void objectSelected(SelectionProvider<T> provider, T selectedObject);
}
