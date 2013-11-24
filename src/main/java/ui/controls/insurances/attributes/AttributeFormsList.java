package ui.controls.insurances.attributes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.insurances.CompanyByInsuranceType;
import model.insurances.Insurance;
import model.insurances.attributes.AttributeType;
import model.insurances.attributes.InsuranceAttribute;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttributeFormsList extends Pane {
    @FXML private VBox rootVBox;

    private AttributeFormsList() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AttributeFormsList.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public AttributeFormsList(Insurance insurance) {
        this();

        setInsurance(insurance);
    }

    public AttributeFormsList(CompanyByInsuranceType companyByInsuranceType) {
        this();

        setCompanyByInsuranceType(companyByInsuranceType);
    }

    public void setInsurance(Insurance insurance) {
        try {
            List<InsuranceAttribute> attributes = insurance.getAttributes();
            for (InsuranceAttribute attribute : attributes)
                rootVBox.getChildren().add(new AttributeForm(attribute));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCompanyByInsuranceType(CompanyByInsuranceType companyByInsuranceType) {
        try {
            List<AttributeType> attributeTypes = companyByInsuranceType.getAttributeTypes();
            for (AttributeType attributeType : attributeTypes)
                rootVBox.getChildren().add(new AttributeForm(attributeType));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit(int insuranceId) {
        List<AttributeForm> forms = (List)rootVBox.getChildren();
        try {
            for (AttributeForm form : forms)
                form.commitObject(insuranceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearForms() {
        List<AttributeForm> forms = (List)rootVBox.getChildren();
        for (AttributeForm form : forms)
            form.clearForm();
    }
}
