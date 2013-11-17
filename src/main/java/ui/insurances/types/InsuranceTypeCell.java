package ui.insurances.types;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.insurances.InsuranceType;

import java.io.IOException;

public class InsuranceTypeCell extends ListCell<InsuranceType> {
    @FXML public Text nameField;
    @FXML public VBox rootVBox;

    public InsuranceTypeCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsuranceTypeCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(InsuranceType insuranceType, boolean b) {
        super.updateItem(insuranceType, b);

        if (insuranceType != null) {
            nameField.setText(insuranceType.getName());
            setGraphic(rootVBox);
        }
    }
}
