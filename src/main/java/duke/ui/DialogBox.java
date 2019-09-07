package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.Collections;

/**
 * Displays the Dialog Box for the commands entered by user and duke replies.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;

    @FXML
    private ImageView displayPicture;

    public DialogBox() {

    }

    /**
     * Creates DialogBox.
     *
     * @param text text entered in the textfield.
     * @param img image to be displayed in the dialogbox (either duke image or user image).
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and formats HBox for user.
     *
     * @param text text entered in the textfield.
     * @param img user image.
     * @return DialogBox that contains the text and img.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextAlignment(TextAlignment.RIGHT);
        return db;
    }

    /**
     * Creates and formats HBox for duke.
     *
     * @param text text entered in the textfield.
     * @param img duke image.
     * @return DialogBox that contains the text and img.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextAlignment(TextAlignment.LEFT);
        db.flip();
        return db;
    }

}
