package model;

import model.input.Input;
import model.input.InputImpl;
import model.object.AbstractTank;
import model.object.EnemyTankImpl;
import model.object.PlayerTankImpl;
import model.utility.Pair;
/**
 * Implementation of Model interface.
 */
public class World implements Model {
    private final static Pair<Double, Double> DIMENSION = new Pair<>(600.0, 400.0);
    private AbstractTank player;
    private AbstractTank enemy;
    private Input playerInput;
    private Input enemyInput;
    /**
     * Constructor.
     * <p>Set player input and enemy input.
     */
    public World() {
    	super();
    }
    
    @Override
    public void configPlayerTank(final Pair<Double, Double> position, int lifes, final double speed, final double projectileSpeed) {
       this.player = new PlayerTankImpl(position, lifes, speed, projectileSpeed);
       this.playerInput = new InputImpl();
    }

    @Override
    public void configEnemyTank(final Pair<Double, Double> position, int lifes, final double speed, final double projectileSpeed) {
        this.enemy = new EnemyTankImpl(position, lifes, speed, projectileSpeed);
        this.enemyInput = new InputImpl();
    }

    @Override
    public AbstractTank getPlayer() {
        return this.player;
    }

    @Override
    public AbstractTank getEnemy() {
        return this.enemy;
    }

    @Override
    public Pair<Double, Double> getBounds() {
        return DIMENSION;
    }

    @Override
    public Input getPlayerInput() {
	return this.playerInput;
    }

    @Override
    public Input getEnemyInput() {
        return this.enemyInput;
    }
   
}
