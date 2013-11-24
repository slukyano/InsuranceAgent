package ui.controls.insurances.attributetypes;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.insurances.attributes.AttributeType;
import ui.controls.AbstractView;

import java.net.URL;

public class AttributeTypeView extends AbstractView<AttributeType> {
    @FXML private Text nameField;

    public AttributeTypeView() {
    }

    public AttributeTypeView(AttributeType data) {
        super(data);
    }

    @Override
    protected void update() {
        if (data != null) {
            nameField.setText(data.getName());
        }
        else {
            nameField.setText("");
        }
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AttributeTypeView.fxml");
    }
}
