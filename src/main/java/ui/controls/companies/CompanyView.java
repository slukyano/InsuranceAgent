package ui.controls.companies;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.Company;
import ui.controls.AbstractView;

import java.net.URL;

public class CompanyView extends AbstractView<Company> {
    @FXML private Text nameField;

    public CompanyView() {
    }

    public CompanyView(Company data) {
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
        return getClass().getResource("CompanyView.fxml");
    }
}
