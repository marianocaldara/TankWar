package controller.levels;

import java.util.Arrays;
import java.util.Iterator;

import controller.Controller;
import controller.loader.FileController;

/**
 * Concrete implementation of {@link FileController} interface.
 */
public final class LevelImpl implements Level { 

    private static final LevelImpl SINGLETON = new LevelImpl();
    private boolean isLevelPaused;
    private boolean isLevelStarted;
    private boolean isLevelEnded;
    private boolean isGameEnded;
    private boolean isGameOver;
    private Iterator<Levels> levels;
    private Levels currentLevel;
    private FileController fileController;
    private Controller controller;

    /**
     * Private constructor.
     */
    private LevelImpl() {
    }

    /**
     * Getter of the object {@link LevelImpl}.
     * 
     * @return the object {@link LevelImpl}.
     */
    public static LevelImpl getLevelImpl() {
        return SINGLETON;
    }

    @Override
    public void initialize(final FileController fileController, final Controller controller) {
        this.isLevelPaused = false;
        this.isLevelStarted = false;
        this.isLevelEnded = false;
        this.isGameEnded = false;
        this.isGameOver = false;
        this.levels = Arrays.asList(Levels.values()).iterator();
        this.currentLevel = this.levels.next();
        this.fileController = fileController;
        this.controller = controller;
    }

    @Override
    public boolean isLevelStarted() {
        return this.isLevelStarted;
    }

    @Override
    public boolean isGameOver() {
        return this.isGameOver;
    }

    @Override
    public boolean isLevelEnded() {
        return this.isLevelEnded;
    }

    @Override
    public boolean isGameEnded() {
        return this.isGameEnded;
    }

    @Override
    public void setLevelStarted() {
        this.isLevelStarted = true;
        this.isLevelEnded = false;
        this.isGameEnded = false;
        this.isGameOver = false;
        this.fileController.loadLevel(this.currentLevel);
        this.controller.initializeObjects();
        this.controller.startGameLoop();

    }

    @Override
    public void setLevelEnded() {
        this.isLevelStarted = false;
        this.isLevelEnded = true;
        this.controller.getGameLoop().stopLoop();
        this.updateLevel();
    }

    @Override
    public void setLevelPaused() {
        if (this.isLevelPaused) {
            this.isLevelPaused = false;
            this.controller.getGameLoop().resumeLoop();
        } else {
            this.isLevelPaused = true;
            this.controller.getGameLoop().pauseLoop();
        }

    }

    @Override
    public Levels getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public void setCurrentLevel(final Levels currentLevel) {
        if (!currentLevel.equals(Levels.LEVEL_1)) {
            this.currentLevel = currentLevel;
            while (this.levels.hasNext() && !this.levels.next().equals(this.currentLevel)) { }
        }
    }

    @Override
    public void setGameOver() {
        this.isGameOver = true;
        this.setGameEnded();
    }

    /**
     * Method that manage the updating of the levels.
     */
    private void updateLevel() {
        if (this.levels.hasNext()) {
            this.currentLevel = this.levels.next();
        } else {
            this.isGameEnded = true;
            this.setGameEnded();
        }
    }

    private void setGameEnded() {
        this.isLevelStarted = false;
        this.controller.getGameLoop().stopLoop();
        this.levels = Arrays.asList(Levels.values()).iterator();
        this.currentLevel = this.levels.next();
    }

}
