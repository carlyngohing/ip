package yapatron;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

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

    private Image yapatronImage = new Image(
        getClass().getResourceAsStream("/images/happyhamster.png"));

    private Image errorImage = new Image(
        getClass().getResourceAsStream(
                "/images/mac-jones-september-11.png"));

    private static final String FONT = loadMinecraftFont();

/**
 * Loads the bundled Minecraft font.
 *
 * @return the loaded font family name
 */
private static String loadMinecraftFont() {
    URL fontUrl = MainWindow.class.getResource("/fonts/Minecraft.ttf");

    if (fontUrl == null) {
        throw new IllegalStateException("Minecraft.ttf was not found");
    }

    Font font = Font.loadFont(fontUrl.toExternalForm(), 13);

    if (font == null) {
        throw new IllegalStateException("Minecraft.ttf could not be loaded");
    }

    return font.getFamily();
}

/**
 * Gets the font family used by the application.
 *
 * @return Minecraft font family
 */
public static String getFont() {
    return FONT;
}
    

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.
        heightProperty());
        String fontStyle = "-fx-font-family: '" + FONT + "';";

userInput.setStyle(fontStyle);
sendButton.setStyle(fontStyle);
    }

    public void setYapatron(Yapatron y) {
        this.yapatron = y;
        dialogContainer.getChildren().add(
                DialogBox.getYapatronDialog("Hello hellooooo"
                    + "!!! My name is Yapatron :D What's the haps for todayayayay?", 
                    yapatronImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isBlank()) {
            return;
        }

        String response = yapatron.getResponse(input);
        Image responseImage = yapatron.wasLastResponseAnError()
                ? errorImage
                : yapatronImage;

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapatronDialog(response, responseImage)
        );
        userInput.clear();
        
        if (input.trim().equalsIgnoreCase("bye")) {
            javafx.application.Platform.exit();
        }
    }
}



                        
