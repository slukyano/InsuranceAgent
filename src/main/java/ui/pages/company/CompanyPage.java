package ui.pages.company;

import model.Company;
import ui.controls.AbstractView;
import ui.controls.companies.CompanyForm;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class CompanyPage extends ViewPage<Company> {
    public CompanyPage(AbstractView<Company> objectView, Company data) {
        super(objectView, data);
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
}
