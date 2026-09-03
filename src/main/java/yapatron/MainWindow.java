package yapatron;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * A Controller for MainWindow
 * Provides the layout for the other controls
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yapatron yapatron;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/1077114.png"));
    private Image yapatronImage = new Image(this.getClass().getResourceAsStream("/images/1077114.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.
        heightProperty());
    }

    public void setYapatron(Yapatron y) {
        this.yapatron = y;
        dialogContainer.getChildren().add(
                DialogBox.getYapatronDialog("Hello!!! My name is Yapatron :D What would you like to do?", yapatronImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        String response = yapatron.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapatronDialog(response, yapatronImage)
        );
        userInput.clear();
        
        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit();
        }
    }
}



                        
