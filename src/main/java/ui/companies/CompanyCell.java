package ui.companies;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Company;

import java.io.IOException;

public class CompanyCell extends ListCell<Company> {
    @FXML public Text nameField;
    @FXML public VBox rootVBox;

    public CompanyCell() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CompanyCell.fxml"));
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(Company company, boolean b) {
        super.updateItem(company, b);

        if (company != null) {
            nameField.setText(company.getName());
            setGraphic(rootVBox);
        }
    }
}
