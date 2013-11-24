package ui.controls.insurances;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.insurances.Insurance;
import ui.controls.AbstractView;

import java.net.URL;

public class InsuranceView extends AbstractView<Insurance> {
    @FXML private Label insuranceType;
    @FXML private Label clientName;
    @FXML private Label agentName;
    @FXML private Label companyName;

    public InsuranceView() {
    }

    public InsuranceView(Insurance data) {
        super(data);
    }

    @Override
    protected void update() {
        if (data != null) {
            insuranceType.setText(data.getInsuranceTypeName());
            clientName.setText(data.getClientName());
            agentName.setText(data.getAgentName());
            companyName.setText(data.getCompanyName());
        }
        else {
            insuranceType.setText("");
            clientName.setText("");
            agentName.setText("");
            companyName.setText("");
        }
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("InsuranceView.fxml");
    }
}
