package ui.controls.insurances.attributes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.ModelController;
import model.insurances.attributes.AttributeType;
import model.insurances.attributes.InsuranceAttribute;
import ui.controls.AbstractView;

import java.net.URL;
import java.sql.SQLException;

public class AttributeForm extends AbstractView<InsuranceAttribute> {
    @FXML private Text nameText;
    @FXML private TextField valueField;
    private AttributeType attributeType;

    public AttributeForm(AttributeType attributeType) {
        this.attributeType = attributeType;
        nameText.setText(attributeType.getName());
    }

    public AttributeForm(InsuranceAttribute data) {
        super(data);
        setData(data);
    }

    public InsuranceAttribute commitObject(int insuranceId) throws SQLException {
        if (data == null)
            setData(createObject(insuranceId));
        else
            setData(updateObject());

        return data;
    }

    private InsuranceAttribute createObject(int insuranceId) throws SQLException {
        return ModelController.getInstance().createInsuranceAttribute(
                attributeType.getAttributeTypeId(),
                valueField.getText(),
                insuranceId);
    }

    private InsuranceAttribute updateObject() throws SQLException {
        return ModelController.getInstance().updateInsuranceAttribute(
                data.getTypeId(),
                valueField.getText(),
                data.getInsuranceId(),
                data.getAttributeId());
    }

    public void clearForm() {
        valueField.setText("");
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("AttributeForm.fxml");
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                nameText.setText(data.getAttributeTypeName());
                valueField.setText(data.getAttributeValue());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            nameText.setText(attributeType.getName());
            valueField.setText("");
        }
    }
}
