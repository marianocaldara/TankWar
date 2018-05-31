package controller.file;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.levels.Levels;
import model.Model;
import model.utility.Pair;

/**
 * Concrete implementation of FileController.
 */
public class FileControllerImpl implements FileController {
	
	private static final String PATH = "res/levels/";
	private static final String EXTENSION = ".json";
	private Levels level;
	private JSONParser json;
	private Model world;
	
	/**
	 * Instance a new FileControllerImpl.
	 * @param level
	 * 		the level to load.
	 */
	public FileControllerImpl(Levels level, Model world) {	
		super();
		this.level = level;
		this.world = world;
		this.json = new JSONParser();
	}
	
	@Override
	public void loadLevel() {
		Object obj;
		JSONObject jsonObject;
		try {
			 obj = this.json.parse(new FileReader(PATH + this.level.getName() + EXTENSION));
			 jsonObject = (JSONObject) obj;
			 JSONObject playerFields = (JSONObject) jsonObject.get("playerTank");
			 JSONObject enemyFields = (JSONObject) jsonObject.get("enemyTank");
			 this.world.configPlayerTank(new Pair<Double, Double>((Double) playerFields.get(InitialStateFields.POSX.getName()), 
					 (Double) playerFields.get(InitialStateFields.POSY.getName())), 
					 (Integer) playerFields.get(InitialStateFields.LIFES.getName()), 
					 (Double) playerFields.get(InitialStateFields.SPEED.getName()));
			 this.world.configEnemyTank(new Pair<Double, Double>((Double) enemyFields.get(InitialStateFields.POSX.getName()),
					 (Double) enemyFields.get(InitialStateFields.POSY.getName())), 
					 (Integer) enemyFields.get(InitialStateFields.LIFES.getName()),
					 (Double) enemyFields.get(InitialStateFields.SPEED.getName()));
			 
		}
		catch(IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
	}

	/*@Override
	public void saveLevel() {
		// TODO Auto-generated method stub

	}*/

	@Override
	public void setLevel(Levels currentLevel) {
		this.level = currentLevel;
		
	}

	

}