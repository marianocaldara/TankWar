package controller.collision;

import java.util.List;
import java.util.stream.Collectors;

import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Pair;

public class TankWithProjectile implements Collision{
	
	private static final int DAMAGE = 1;
	private AbstractTank playerTank;
	private AbstractTank enemyTank;
	private List<Projectile> projectiles;
	
	public TankWithProjectile(AbstractTank playerTank, AbstractTank enemyTank, List<Projectile> projectiles) {
		this.playerTank = playerTank;
		this.enemyTank = enemyTank;
		this.projectiles = projectiles;
	}

	@Override
	public void manageCollision() {
		updateTankLifeAndProjectiles(this.playerTank, 
				projectiles.stream().filter(p -> intersects(p.getPosition(), p.getBounds(), this.playerTank.getPosition(), 
						this.playerTank.getDimension())).collect(Collectors.toList()));
		updateTankLifeAndProjectiles(this.enemyTank, 
				projectiles.stream().filter(p -> intersects(p.getPosition(), p.getBounds(), this.enemyTank.getPosition(), 
						this.enemyTank.getDimension())).collect(Collectors.toList()));
		
	}
	
	private static void updateTankLifeAndProjectiles(AbstractTank tank, List<Projectile> hitProjectiles) {
		if(!hitProjectiles.isEmpty()) {
			tank.damage(DAMAGE * hitProjectiles.size());
			hitProjectiles.forEach(p -> p.setDead());
		}
	}	
	
	private static boolean intersects(Pair<Double, Double> positionFirst, Pair<Double, Double> dimensionFirst, Pair<Double, Double> positionSecond, Pair<Double, Double> dimensionSecond) {
		if(positionFirst == null || dimensionFirst == null || positionSecond == null || dimensionSecond == null) {
			return false;
		}
		return positionFirst.getFirst() + dimensionFirst.getFirst() > positionSecond.getFirst() &&
				positionFirst.getSecond() + dimensionFirst.getSecond() > positionSecond.getSecond() &&
				positionFirst.getFirst() < positionSecond.getFirst() + dimensionSecond.getFirst() &&
				positionFirst.getSecond() < positionSecond.getSecond() + dimensionSecond.getSecond();
	}

}
