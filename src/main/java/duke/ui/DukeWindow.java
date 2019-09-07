package duke.ui;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.shared.Messages;
import duke.storage.Storage;
import duke.task.TaskList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Displays the Duke GUI. Executes the command when user enters it in the Duke GUI.
 */
public class DukeWindow extends AnchorPane {
    private Stage stage;
    private Storage storage;
    private TaskList tasks;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public DukeWindow() {

    }

    /**
     * Creates DukeWindow Ui constructor.
     *
     * @param stage primary stage.
     * @param storage storage object for this Ui.
     * @param tasks a list of tasks for this Ui.
     */
    public DukeWindow(Stage stage, Storage storage, TaskList tasks) {
        this.stage = stage;
        this.storage = storage;
        this.tasks = tasks;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Loads the DukeWindows.fxml.
     */
    public void show()  {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DukeWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(stage);
            fxmlLoader.load();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showWelcome();
    }

    /**
     * Reads user input and run the commands.
     */
    @FXML
    private void handleUserInput() {
        String fullCommand = userInput.getText().trim();
        dialogContainer.getChildren().add(DialogBox.getUserDialog("Command enter: " + fullCommand, user));
        boolean isExit = false;
        try {
            Command c = Parser.parse(fullCommand);
            String outputString = c.execute(tasks, storage);
            if (c.isExit()) {
                System.exit(0);
            }
            outputToUi(outputString);
        } catch (DukeException e) {
            outputToUi(e.getMessage());
        } catch (NumberFormatException e) {
            outputToUi(String.format(Messages.DESCRIPTION_FORMAT_EXCEPTION, "number"));
        }
        userInput.clear();
    }

    /**
     * Prints the message in the Ui.
     *
     * @param message message to be printed.
     */
    public void outputToUi(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, duke));
    }

    /**
     * Prints welcome message to user.
     */
    public void showWelcome() {
        outputToUi(Messages.GREETING_MESSAGE);
    }
}