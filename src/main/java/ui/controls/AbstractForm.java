package ui.controls;

import java.sql.SQLException;

public abstract class AbstractForm<T> extends AbstractView<T> {
    protected abstract T createObject() throws SQLException;

    protected abstract T updateObject() throws SQLException;

    public T commitObject() throws SQLException {
        if (data == null)
            setData(createObject());
        else
            setData(updateObject());

        return data;
    }

    public abstract void clearForm();
}
