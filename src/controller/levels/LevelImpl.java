package controller.levels;

import java.util.*;

public class LevelImpl implements Level {
	
	private boolean levelStarted;
	private boolean levelEnded;
	private boolean gameEnded;
	private Iterator<Levels> levels;
	private Levels currentLevel;
	
	public LevelImpl() {
		this.levelStarted = false;
		this.levelEnded = false;
		this.gameEnded = false;
		this.levels = Arrays.asList(Levels.values()).iterator();
		this.currentLevel = this.levels.next();
	}

	@Override
	public boolean isLevelStarted() {
		return this.levelStarted;
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
	}

	@Override
	public void setLevelEnded() {
		this.levelEnded = true;	
	}

	@Override
	public Levels getCurrentLevel() {
		return this.currentLevel;
	}

	@Override
	public void updateLevel() {
		if(this.levels.hasNext()) {
			this.currentLevel = this.levels.next();
		}
		else {
			this.gameEnded = true;
		}
	}

	

}
