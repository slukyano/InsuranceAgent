package ui.controls.insurances.insurancetypes;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.insurances.InsuranceType;
import ui.controls.AbstractView;

import java.net.URL;

public class InsuranceTypeView extends AbstractView<InsuranceType> {
    @FXML private Text nameField;

    public InsuranceTypeView() {
    }

    public InsuranceTypeView(InsuranceType data) {
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
        return getClass().getResource("InsuranceTypeView.fxml");
    }
}
