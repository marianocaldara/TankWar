package controller.collision;

import java.util.List;

import model.object.Projectile;
import model.utility.Pair;

public class ProjectileWithProjectile implements Collision {
	
	private List<Projectile> projectiles;
	
	public ProjectileWithProjectile(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	@Override
	public void manageCollision() {
		for(Projectile p : projectiles) {
			for(Projectile x : projectiles) {
				if(intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds()) && x != p) {
					p.setDead();
				}
			}
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
