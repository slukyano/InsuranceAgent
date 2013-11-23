package ui.controls;

public abstract class AbstractPicker<T> extends AbstractReferenceView<T> implements SelectionListener<T> {
    public abstract void pickObject();

    protected AbstractPicker() {
        valueText.setText("<click to pick>");
    }

    protected AbstractPicker(T data) {
        super(data);
    }

    @Override
    public final void objectSelected(SelectionProvider<T> provider, T selectedObject) {
        setData(selectedObject);
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
