package ui.controls.insurances.attributetypes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttributeTypeFormsList extends Pane {
    @FXML private VBox rootVBox;

    public AttributeTypeFormsList() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AttributeTypeFormsList.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void addAttributeType() {
        rootVBox.getChildren().add(new AttributeTypeForm());
    }

    public void commit(int cbitId) {
        List<AttributeTypeForm> forms = (List)rootVBox.getChildren();
        try {
            for (AttributeTypeForm form : forms)
                form.commitObject(cbitId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearForms() {
        List<AttributeTypeForm> forms = (List)rootVBox.getChildren();
        for (AttributeTypeForm form : forms)
            form.clearForm();
    }
}
