package controller.filecontroller;

import org.json.simple.parser.JSONParser;

import controller.levels.Levels;

/**
 * Concrete implementation of FileController.
 */
public class FileControllerImpl implements FileController {
	
	private static final String PATH = "res/levels/";
	private static final String EXTENSION = ".json";
	private Levels level;
	private JSONParser json;
	
	/**
	 * Instance a new FileControllerImpl.
	 */
	public FileControllerImpl() {
		super();
	}
	
	/**
	 * Instance a new FileControllerImpl.
	 * @param level
	 * 		the level to load.
	 */
	public FileControllerImpl(final Levels level) {	
		super();
		this.level = level;
		this.json = new JSONParser();
	}
	
	@Override
	public void loadLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveLevel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLevel(final Levels currentLevel) {
		this.level = currentLevel;
		
	}

	

}
