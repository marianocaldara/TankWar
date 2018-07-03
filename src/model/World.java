package model;

import model.input.InputImpl;
import model.object.Tank;
import model.object.TankImpl;
import model.utility.Pair;

public class World implements Model {
    private final static Pair<Double, Double> DIMENSION = new Pair<>(600.0, 400.0);
    private Tank player;
    private Tank enemy;
    private InputImpl inputPlayer = new InputImpl();
    private InputImpl inputEnemy = new InputImpl();
    
    @Override
    public void configPlayerTank(final Pair<Double, Double> position, int lifes, final double speed) {
       player = new TankImpl(position, lifes, speed);
    }

    @Override
    public void configEnemyTank(final Pair<Double, Double> position, int lifes, final double speed) {
        enemy = new TankImpl(position, lifes, speed);
    }

    @Override
    public Tank getPlayer() {
        return player;
    }

    @Override
    public Tank getEnemy() {
        return enemy;
    }

    @Override
    public Pair<Double, Double> getBounds() {
        return DIMENSION;
    }

    @Override
    public InputImpl getInputPlayer() {
        return this.inputPlayer;
    }

    @Override
    public InputImpl getInputEnemy() {
        return this.inputEnemy;
    }
}
