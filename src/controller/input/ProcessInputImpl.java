package controller.input;

import controller.levels.Level;
import controller.objects.ControllerObjects;
import controller.utility.Convertitor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of {@link ProcessInput} interface.
 */
public final class ProcessInputImpl implements ProcessInput {

    private static final ProcessInputImpl SINGLETON = new ProcessInputImpl();
    private ControllerObjects controllerObjects;
    private Level level;


    private ProcessInputImpl() { }

    /**
     * Getter of the controller input.
     * @return a {@link ProcessInputImpl}.
     */
    public static ProcessInputImpl getProcessInput() {
        return SINGLETON;
    }

    @Override
    public void initialize(final ControllerObjects controllerObjects, final Level level) {
        this.controllerObjects = controllerObjects;
        this.level = level;

    }

    @Override
    public void setKeyInput(final KeyEvent event, final boolean b) {
        switch (event.getCode()) {
        case UP:
            this.controllerObjects.movePlayerTank(Direction.UP, b);
            break;
        case DOWN:
            this.controllerObjects.movePlayerTank(Direction.DOWN, b);
            break;
        case LEFT:
            this.controllerObjects.movePlayerTank(Direction.LEFT, b);
            break;
        case RIGHT:
            this.controllerObjects.movePlayerTank(Direction.RIGHT, b);
            break;
        case W:
            this.controllerObjects.movePlayerTank(Direction.UP, b);
            break;
        case S:
            this.controllerObjects.movePlayerTank(Direction.DOWN, b);
            break;
        case A:
            this.controllerObjects.movePlayerTank(Direction.LEFT, b);
            break;
        case D:
            this.controllerObjects.movePlayerTank(Direction.RIGHT, b);
            break;
        case P:
            if (b) {
                this.level.setLevelPaused();
            }
        default:
        }

    }

    @Override
    public void setMouseMovement(final MouseEvent event) {
        this.controllerObjects.movePlayerCannon(Convertitor.viewToModel(new Pair<Double, Double>(event.getSceneX(), event.getSceneY())));

    }

    @Override
    public void setMouseClicked(final MouseEvent event) {
        switch (event.getButton()) {
        case PRIMARY:
            this.controllerObjects.playerShot();
            break;
        default:
        }

    }

}
