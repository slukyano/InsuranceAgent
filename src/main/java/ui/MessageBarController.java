package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MessageBarController implements Initializable {
    @FXML private Button okButton;
    @FXML private Button yesButton;
    @FXML private Button noButton;
    @FXML private BorderPane rootContainer;
    @FXML private Text messageText;
    @FXML private HBox buttonContainer;
    static MessageBarController instance;

    static public MessageBarController getInstance() {
        return instance;
    }

    public void showMessage(String message) {
        messageText.setText(message);
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootContainer.setVisible(false);
                rootContainer.setManaged(false);
            }
        });

        yesButton.setVisible(false);
        yesButton.setManaged(false);

        noButton.setVisible(false);
        noButton.setManaged(false);

        okButton.setVisible(true);
        okButton.setManaged(true);

        rootContainer.setVisible(true);
        rootContainer.setManaged(true);
    }

    public void showQuestion(String message, final AnswerListener answerListener) {
        messageText.setText(message);
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootContainer.setVisible(false);
                rootContainer.setManaged(false);
                answerListener.yes();
            }
        });
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                rootContainer.setVisible(false);
                rootContainer.setManaged(false);
                answerListener.no();
            }
        });

        yesButton.setVisible(true);
        yesButton.setManaged(true);

        noButton.setVisible(true);
        noButton.setManaged(true);

        okButton.setVisible(false);
        okButton.setManaged(false);

        rootContainer.setVisible(true);
        rootContainer.setManaged(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instance = this;
        rootContainer.setVisible(false);
        rootContainer.setManaged(false);
    }
}
