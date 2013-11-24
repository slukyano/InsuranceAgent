package ui.controls;

import java.sql.SQLException;

public abstract class AbstractForm<T> extends AbstractView<T> {
    protected abstract T createObject() throws SQLException;

    protected abstract T updateObject() throws SQLException;

    protected AbstractForm() {
    }

    protected AbstractForm(T data) {
        super(data);
    }

    public T commitObject() throws SQLException {
        if (data == null)
            setData(createObject());
        else
            setData(updateObject());

        return data;
    }

    public abstract void clearForm();

    public abstract boolean canSubmit();
}
