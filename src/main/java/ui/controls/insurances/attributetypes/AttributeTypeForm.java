package ui.controls.insurances.attributetypes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ModelController;
import model.insurances.attributes.AttributeType;
import ui.controls.AbstractView;

import java.net.URL;
import java.sql.SQLException;

public class AttributeTypeForm extends AbstractView<AttributeType> {
    @FXML private TextField nameField;
    @FXML private TextField descriptionField;

    public AttributeType commitObject(int cbitId) throws SQLException {
        if (data == null)
            setData(createObject(cbitId));
        else
            setData(updateObject());

        return data;
    }

    private AttributeType createObject(int cbitId) throws SQLException {
        return ModelController.getInstance().createAttributeType(
                nameField.getText(),
                descriptionField.getText(),
                cbitId);
    }

    private AttributeType updateObject() throws SQLException {
        return ModelController.getInstance().updateAttributeType(
                nameField.getText(),
                descriptionField.getText(),
                data.getAttributeTypeId(),
                data.getCbitID());
    }

    public void clearForm() {
        nameField.setText("");
        descriptionField.setText("");
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AttributeTypeForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getName());
            descriptionField.setText(data.getDescription());
        }
        else {
            clearForm();
        }
    }
}
