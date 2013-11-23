package ui.controls.companies;

import model.Company;
import ui.UiRootController;
import ui.controls.AbstractReferenceView;
import ui.pages.company.CompanyPage;

public class CompanyReferenceView extends AbstractReferenceView<Company> {
    public CompanyReferenceView() {
    }

    public CompanyReferenceView(Company data) {
        super(data);
    }

    @Override
    protected void onMouseClicked() {
        UiRootController.getInstance().navigateForward(
                new CompanyPage(data),
                data.getName());
    }

    @Override
    protected String getValueString() {
        return data != null ? data.getName() : "";
    }
}
