package controller.loader;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.levels.Levels;
import model.Model;
import model.utility.Pair;

/**
 * Concrete implementation of {@link FileController} interface.
 */
public class FileControllerImpl implements FileController {
	
	private static final String PATH = "res/levels/";
	private static final String EXTENSION = ".json";
	private JSONParser json;
	private Model world;
	
	/**
	 * Instance a new FileController.
	 * @param world
	 * 		the {@link Model} of the game.
	 */
	public FileControllerImpl(Model world) {	
		super();
		this.world = world;
		this.json = new JSONParser();
	}
	
	@Override
	public void loadLevel(Levels level) {
		Object obj;
		JSONObject jsonObject;
		try {
			obj = this.json.parse(new FileReader(PATH + level.getName() + EXTENSION));
			jsonObject = (JSONObject) obj;
			JSONObject playerFields = (JSONObject) jsonObject.get("playerTank");
			JSONObject enemyFields = (JSONObject) jsonObject.get("enemyTank");

			this.world.configPlayerTank(
					new Pair<Double, Double>((Double) playerFields.get(InitialStateFields.POSX.getName()),
							(Double) playerFields.get(InitialStateFields.POSY.getName())),
					((Long) playerFields.get(InitialStateFields.LIFES.getName())).intValue(),
					(Double) playerFields.get(InitialStateFields.SPEED.getName()),
					(Double) playerFields.get(InitialStateFields.P_SPEED.getName()));
			this.world.configEnemyTank(
					new Pair<Double, Double>((Double) enemyFields.get(InitialStateFields.POSX.getName()),
							(Double) enemyFields.get(InitialStateFields.POSY.getName())),
					((Long) enemyFields.get(InitialStateFields.LIFES.getName())).intValue(),
					(Double) enemyFields.get(InitialStateFields.SPEED.getName()),
					(Double) enemyFields.get(InitialStateFields.P_SPEED.getName()));

		}
		catch(IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
	}

	/*@Override
	public void saveLevel() {
		// TODO Auto-generated method stub

	}*/	

}