package ui.insurances.attributes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.insurances.attributes.InsuranceAttribute;

import java.io.IOException;
import java.sql.SQLException;

public class AttributeCell extends ListCell<InsuranceAttribute> {
    @FXML public VBox rootVBox;
    public Text attributeName;
    public Text attributeValue;

    public AttributeCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttributeCell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(InsuranceAttribute insuranceAttribute, boolean b) {
        super.updateItem(insuranceAttribute, b);

        if (insuranceAttribute != null) {
            try {
                attributeName.setText(insuranceAttribute.getAttributeTypeName());
                attributeName.setText(insuranceAttribute.getAttributeValue());
                setGraphic(rootVBox);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}