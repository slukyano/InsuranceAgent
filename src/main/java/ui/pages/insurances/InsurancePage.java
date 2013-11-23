package ui.pages.insurances;

import model.insurances.Insurance;
import ui.controls.AbstractView;
import ui.controls.insurances.InsuranceForm;
import ui.pages.EditPage;
import ui.pages.ViewPage;

import java.net.URL;

public class InsurancePage extends ViewPage<Insurance> {
    public InsurancePage(AbstractView<Insurance> objectView, Insurance data) {
        super(objectView, data);
    }

    @Override
    protected EditPage<Insurance> editPageFactory(Insurance dataToEdit) {
        return new EditPage<Insurance>(new InsuranceForm(), dataToEdit);
    }

    @Override
    protected String editPageTitle() {
        return "Edit insurance";
    }

    @Override
    protected URL getFxmlUrl() {
        return getClass().getResource("InsurancePage.fxml");
    }
}
