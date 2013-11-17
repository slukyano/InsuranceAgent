package ui.insurances;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.insurances.Insurance;

import java.io.IOException;
import java.sql.SQLException;

public class InsuranceCell extends ListCell<Insurance> {
    @FXML public VBox rootVBox;
    @FXML public Text insuranceType;
    @FXML public Text companyName;
    @FXML public Text clientName;
    @FXML public Text agentName;

    public InsuranceCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InsuranceCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(Insurance insurance, boolean b) {
        super.updateItem(insurance, b);

        if (insurance != null) {
            try {
                insuranceType.setText(insurance.getInsuranceType().getName());
                companyName.setText(insurance.getCompany().getName());
                clientName.setText(insurance.getClient().getName());
                agentName.setText(insurance.getAgent().getFullName());
                setGraphic(rootVBox);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}