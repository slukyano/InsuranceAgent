package ui.controls;

import ui.UiRootController;

import java.util.HashSet;

public abstract class AbstractPicker<T> extends AbstractReferenceView<T>
        implements SelectionProvider<T>, SelectionListener<T>
{
    private final HashSet<SelectionListener<T>> selectionListeners = new HashSet<SelectionListener<T>>();

    public abstract void pickObject();

    protected AbstractPicker() {
        valueText.setText("<click to pick>");
    }

    protected AbstractPicker(T data) {
        super(data);
    }

    private void notifyListeners(T selectedObject) {
        for (SelectionListener<T> listener : selectionListeners)
            listener.objectSelected(this, selectedObject);
    }

    @Override
    public void addSelectionListener(SelectionListener<T> listener) {
        selectionListeners.add(listener);
    }

    @Override
    public void removeSelectionListener(SelectionListener<T> listener) {
        selectionListeners.remove(listener);
    }

    @Override
    public final void objectSelected(SelectionProvider<T> provider, T selectedObject) {
        setData(selectedObject);
        notifyListeners(selectedObject);
        UiRootController.getInstance().navigateBack();
    }

    @Override
    protected final void onMouseClicked() {
        pickObject();
    }

    @Override
    protected void update() {
        if (data != null)
            valueText.setText(getValueString());
        else
            valueText.setText("<click to pick>");
    }
}
