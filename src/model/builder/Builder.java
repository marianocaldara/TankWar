package model.builder;

import model.object.EnemyTank;
import model.object.PlayerTank;
import model.object.Tank;
import model.utility.Pair;

public class Builder {
    private boolean isPlayer;
    private int lifes;
    private Pair<Double, Double> position;
    private double speed;
    private double projectileSpeed;
    private int counter;
    
    public Builder setPlayer(final boolean isPlayer) {
        this.isPlayer=isPlayer;
        counter++;
        return this;
    }
    
    public Builder setLifes(final int lifes) {
        this.lifes=lifes;
        counter++;
        return this;
    }
    
    public Builder setPosition(final Pair<Double, Double> position) {
        this.position=position;
        counter++;
        return this;
    }
    
    public Builder setSpeed(final double speed) {
        if(isPlayer) {
            
        }
        this.speed = speed;
        counter++;
        return this;
    }
    
    public Builder setProjectileSpeed(final double speed) {
        if(isPlayer) {
            
        }
        this.projectileSpeed = speed;
        counter++;
        return this;
    }
    
    private boolean check() {
        if(isPlayer) {
            return counter==3;
        }
        return counter==5;
    }
    
    Tank build() {
        if(!check()) {
            throw new IllegalStateException("All field of Builder are not configured");
        }
        if(isPlayer) {
            return new PlayerTank(position, lifes, speed, projectileSpeed);
        }
        return new EnemyTank(position, lifes, speed, projectileSpeed);
    }
    
}
