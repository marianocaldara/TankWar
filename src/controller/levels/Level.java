package controller.levels;

import controller.Controller;
import controller.loader.FileController;

/**
 * Interface for the levels' control.
 */
public interface Level {

    /**
     * Initialize the fields.
     * @param fileController
     *          the {@link FileController}.
     * @param controller
     *          the game {@link Controller}.
     */
    void initialize(FileController fileController, Controller controller);

    /**
     * Control if the level is started.
     * 
     * @return true if the level is started, false otherwise.
     */
    boolean isLevelStarted();

    /**
     * Control if the level is ended.
     * 
     * @return true if the level is ended, false otherwise.
     */
    boolean isLevelEnded();

    /**
     * Control if the player lose.
     * 
     * @return true if the player lose the game, false otherwise.
     */
    boolean isGameOver();

    /**
     * Set the current level started, load the current level file,initialize the
     * {@link ControllerObjects} and start the {@link GameLoop}.
     */
    void setLevelStarted();

    /**
     * Set to current level ended and stop the {@link GameLoop}.
     */
    void setLevelEnded();

    /**
     * Getter of the current level play.
     * 
     * @return the current level.
     */
    Levels getCurrentLevel();

    /**
     * Setter for the current level.
     * 
     * @param currentLevel
     *            the current level.
     */
    void setCurrentLevel(Levels currentLevel);

    /**
     * Control if the game is ended.
     * 
     * @return true if the game is ended, false otherwise.
     */
    boolean isGameEnded();

    /**
     * Set the game ended, stop the game loop and re initialize the levels.
     */
    void setGameOver();

}
