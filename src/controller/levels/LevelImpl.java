package controller.levels;

import java.util.*;

import controller.Controller;
import controller.loader.FileController;

/**
 *	Concrete implementation of {@link Level} interface.
 */
public class LevelImpl implements Level {
	
	private boolean levelStarted;
	private boolean levelEnded;
	private boolean gameEnded;
	private boolean gameOver;
	private Iterator<Levels> levels;
	private Levels currentLevel;
	private FileController fileController;
	private Controller controller;
	
	/**
	 * Constructor.
	 * @param fileController
	 * 		the {@link FileController}.
	 * @param controller
	 * 		the {@link Controller} of the game.
	 */
	public LevelImpl(FileController fileController, Controller controller) {
		this.levelStarted = false;
		this.levelEnded = false;
		this.gameEnded = false;
		this.gameOver = false;
		this.levels = Arrays.asList(Levels.values()).iterator();
		this.currentLevel = this.levels.next();
		this.fileController = fileController;
		this.controller = controller;
	}

	@Override
	public boolean isLevelStarted() {
		return this.levelStarted;
	}
	
	@Override
	public boolean isGameOver() {
		return this.gameOver;
	}

	@Override
	public boolean isLevelEnded() {
		return this.levelEnded;
	}
	
	@Override
	public boolean isGameEnded() {
		return this.gameEnded;
	}
	
	@Override
	public void setLevelStarted() {
		this.levelStarted = true;
		this.levelEnded = false;
		this.gameEnded = false;
		this.gameOver = false;
		this.fileController.loadLevel(this.currentLevel);
		this.controller.initializeObjects();
		this.controller.startGameLoop();
		
	}

	@Override
	public void setLevelEnded() {
		this.levelStarted = false;
		this.levelEnded = true;
		this.controller.getGameLoop().stopLoop();
		this.updateLevel();
	}

	@Override
	public Levels getCurrentLevel() {
		return this.currentLevel;
	}
	
	@Override
	public void setCurrentLevel(Levels currentLevel) {
		this.currentLevel = currentLevel;
		while(this.levels.hasNext() && !this.levels.next().equals(this.currentLevel));
	}
	
	@Override
	public void setGameOver() {
		this.gameOver = true;
		this.setGameEnded();
	}	
	
	/**
	 * Method that manage the updating of the levels.
	 */
	private void updateLevel() {
		if(this.levels.hasNext()) {
			this.currentLevel = this.levels.next();
		}
		else {
			this.gameEnded = true;
			this.setGameEnded();
		}
	}
	
	private void setGameEnded() {
		this.levelStarted = false;
		this.controller.getGameLoop().stopLoop();
		this.levels = Arrays.asList(Levels.values()).iterator();
		this.currentLevel = this.levels.next();	
	}

	


}
