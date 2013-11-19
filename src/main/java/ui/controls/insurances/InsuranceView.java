package ui.controls.insurances;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.insurances.Insurance;
import ui.controls.AbstractView;

import java.net.URL;
import java.sql.SQLException;

public class InsuranceView extends AbstractView<Insurance> {
    @FXML private Text insuranceType;
    @FXML private Text clientName;
    @FXML private Text agentName;
    @FXML private Text companyName;

    public InsuranceView() {
    }

    public InsuranceView(Insurance data) {
        super(data);
    }

    @Override
    protected void update() {
        if (data != null) {
            try {
                insuranceType.setText(data.getInsuranceType().getName());
                clientName.setText(data.getClient().getName());
                agentName.setText(data.getAgent().getFullName());
                companyName.setText(data.getCompany().getName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
