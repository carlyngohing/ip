package yapatron;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * An HBox representing a dialog box consisting of an ImageView and a Label.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        dialog.setWrapText(true);
        dialog.setPrefWidth(500.0);
        dialog.setMaxWidth(650.0);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db =  new DialogBox(text, img);
        db.dialog.setStyle(
                "-fx-background-color: #1976D2;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 9 13 9 13;"
                + "-fx-text-fill: white;"
                + "-fx-font-family: '" + MainWindow.getFont() + "';"
                + "-fx-font-size: 15px;"
                );
        return db;
    }

    public static DialogBox getYapatronDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(
                "-fx-background-color: #E8EEF2;"
                + "-fx-background-radius: 12;"
                + "-fx-padding: 9 13 9 13;"
                + "-fx-text-fill: #263238;"
                + "-fx-font-family: '" + MainWindow.getFont() + "';"
                + "-fx-font-size: 15px;"
                );
        return db;
    }
}
