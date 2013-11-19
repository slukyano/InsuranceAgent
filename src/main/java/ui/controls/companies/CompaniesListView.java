package ui.controls.companies;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.Company;
import ui.controls.AbstractListView;

public class CompaniesListView extends AbstractListView<Company> {
    public CompaniesListView() {
    }

    public CompaniesListView(ObservableList<Company> companies) {
        super();
        setItems(companies);
    }

    @Override
    protected Node cellGraphicFactory(Company dataObject) {
        return new CompanyView(dataObject);
    }
}
