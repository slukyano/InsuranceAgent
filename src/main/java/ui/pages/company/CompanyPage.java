package ui.pages.company;

import javafx.fxml.FXML;
import model.Company;
import ui.controls.companies.CompanyForm;
import ui.controls.companies.CompanyView;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class CompanyPage extends ViewPage<Company> {
    @FXML private CompanyView companyView;

    public CompanyPage(Company data) {
        super(data);
    }

    @Override
    protected EditPage<Company> editPageFactory(Company dataToEdit) {
        return new EditPage<Company>(new CompanyForm(), dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit company";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("CompanyPage.fxml");
    }

    @Override
    protected void update() {
        companyView.setData(data);
    }
}
