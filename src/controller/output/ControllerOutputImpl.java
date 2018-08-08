package controller.output;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.utility.Convertitor;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Pair;

public class ControllerOutputImpl implements ControllerOutput {
	
	private List<Projectile> projectiles;
	private Tank playerTank;
	private Tank enemyTank;
	
	public ControllerOutputImpl(List<Projectile> projectiles, Tank playerTank, Tank enemyTank) {
		this.projectiles = projectiles;
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
	}

	@Override
	public List<Pair<Double, Double>> getProjectiles() {
		return Collections.unmodifiableList(this.projectiles.stream().map(p -> Convertitor.modelToView(p.getPosition())).collect(Collectors.toList()));
	}

	@Override
	public Pair<Double, Double> getProjectileDimension() {
		return Convertitor.modelToView(this.projectiles.get(0).getBounds());
	}

	@Override
	public Pair<Double, Double> getPlayerPosition() {
		return Convertitor.modelToView(this.playerTank.getPosition());
	}

	@Override
	public Pair<Double, Double> getEnemyPosition() {
		return Convertitor.modelToView(this.enemyTank.getPosition());
	}

	@Override
	public int getPlayerLifes() {
		return this.playerTank.getLifes();
	}

	@Override
	public int getEnemyLifes() {
		return this.enemyTank.getLifes();
	}

	@Override
	public Pair<Double, Double> getTankDimension() {
		return Convertitor.modelToView(this.playerTank.getDimension());
	}

	@Override
	public boolean isPlayerAlive() {
		return this.playerTank.isAlive();
	}

	@Override
	public boolean isEnemyAlive() {
		return this.enemyTank.isAlive();
	}

	@Override
	public Pair<Double, Double> getCannonDimension() {
		return Convertitor.modelToView(this.playerTank.getCannonDimension());
	}

	@Override
	public Pair<Double, Double> getPlayerCannonPosition() {
		return Convertitor.modelToView(this.playerTank.getCannonPosition());
	}

	@Override
	public Pair<Double, Double> getEnemyCannonPosition() {
		return Convertitor.modelToView(this.enemyTank.getCannonPosition());
	}

	@Override
	public double getPlayerAngle() {
		return this.playerTank.getAngle();
	}

	@Override
	public double getEnemyAngle() {
		return this.enemyTank.getAngle();
	}

}
