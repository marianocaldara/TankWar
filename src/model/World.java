package model;

import model.object.Tank;
import model.object.TankImpl;
import model.utility.Pair;

public class World implements Model {
    private final static Pair<Double, Double> DIMENSION = new Pair<>(600.0, 400.0);
    private Tank player;
    private Tank enemy;
    @Override
    public void configPlayerTank(final Pair<Double, Double> position, final int lifes, final double speed) {
       player = new TankImpl(position, lifes, speed);
    }

    @Override
    public void configEnemyTank(final Pair<Double, Double> position, final int lifes, final double speed) {
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

}
