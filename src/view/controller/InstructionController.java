package view.controller;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import view.scene.ViewScenes;
import view.utility.ViewUtils;

/**
 * Controller class for instruction.
 */
public class InstructionController extends ViewController {

    private Controller controller;

    @FXML
    private GridPane instructionGrid;

    @FXML
    private ImageView exitButton;

    /**
     * Allow to return to the settings stage.
     * 
     * @param event
     *            the mouse event.
     */
    @FXML
    void exitAction(MouseEvent event) {
        try {
            ViewScenes.SETTING.setGameStage(ViewUtils.getScene().getWidth(), ViewUtils.getScene().getHeight(),
                    controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(Controller controller) {
        this.controller = controller;

    }

}
