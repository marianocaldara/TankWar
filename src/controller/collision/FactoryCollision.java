package controller.collision;

import java.util.List;
import java.util.Map;

import model.object.AbstractTank;
import model.object.Projectile;
import model.utility.Direction;

public interface FactoryCollision {
	
	Collision tankWithTankCollision(AbstractTank playerTank, AbstractTank enemyTank, Map<Direction, Boolean> movement);
	
	Collision tankWithBordersCollision(AbstractTank tank);
	
	Collision tankWithProjectileCollision(AbstractTank playerTank, AbstractTank enemyTank, List<Projectile> projectiles);
	
	Collision projectileWithBordersCollision(Projectile projectile);
	
	Collision projectileWithProjectileCollision(List<Projectile> projectiles);

}
