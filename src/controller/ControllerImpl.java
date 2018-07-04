package controller;

import java.util.ArrayList;

import controller.collision.Collision;
import controller.collision.CollisionImpl;
import controller.file.FileController;
import controller.file.FileControllerImpl;
import controller.input.ControllerInputImpl;
import controller.levels.Level;
import controller.levels.LevelImpl;
import controller.objects.ControllerObjects;
import controller.utility.Convertitor;
import controller.utility.ConvertitorImpl;
import model.Model;
import view.ViewMain;

/*
import controller.collision.Collision;
import controller.file.FileController;
import controller.input.ControllerInputImpl;
import controller.levels.Level;
import controller.objects.ControllerObjects;
import controller.utility.Convertitor;
import model.Model;
*/

/**
 * Concrete implementation of {@link Controller}
 */

public class ControllerImpl implements Controller {
	private ControllerObjects controllerObject;
	private Convertitor convertitor;
	private Level level;
	private ControllerInputImpl controllerInput;
	private FileController file;
	private Collision collision;
	private Model world;
	private ViewMain view;
	
	public ControllerImpl(Model world, ViewMain view) {
		this.world = world;
		this.view = view;
		this.level = new LevelImpl();
		this.convertitor = new ConvertitorImpl(this.world.getBounds(), this.view.getBounds());
		this.collision = new CollisionImpl(this.world, new ArrayList<>());
		this.controllerObject = new ControllerObjects(this.world.getPlayer(), this.world.getEnemy(), this.world.getInputPlayer(), this.convertitor, this.collision);
		this.file = new FileControllerImpl(this.world);
		this.controllerInput = new ControllerInputImpl();
		file.loadLevel();
	}

	@Override
	public ControllerObjects getControllerObject() {
		return this.controllerObject;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

	@Override
	public ControllerInputImpl getControllerInput() {
		return this.controllerInput;
	}
	

}
