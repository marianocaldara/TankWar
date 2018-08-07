package model.object;

import model.utility.Calculate;
import model.utility.Pair;

public class NewTank extends PlayerTankImpl{
    
    public NewTank(Pair<Double, Double> position, int lifes, double speed, double projectileSpeed) {
        super(position, lifes, speed, projectileSpeed);
    }
    
    private class Cannon extends PlayerTankImpl.Cannon{
        
        @Override
        public void update(final Pair<Double, Double> position, final Pair<Double, Double> target) {
            this.cannonPosition = new Pair<Double, Double>(position.getFirst() - DIMENSION.getFirst() / 2,
                    position.getSecond() + DIMENSION.getSecond() / 2 - cannonDimension.getSecond() / 2);
            this.angle = Calculate.angle(new Pair<>(cannonPosition.getFirst(), cannonPosition.getSecond() + cannonDimension.getSecond() / 2), target);
        }
        
        @Override
        public Projectile shot() {
            if (this.angle >= 30 && this.angle < 90) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + cannonDimension.getFirst() / 2
                        + (DIMENSION.getSecond() * Math.tan(Math.toRadians(90 - this.angle))),
                        position.getSecond() + DIMENSION.getSecond()), this.angle, projectileSpeed);
            } else if (this.angle >= 0 && this.angle < 30) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(position.getFirst() + cannonDimension.getFirst(),
                                position.getSecond() + DIMENSION.getSecond() / 2
                                + (cannonDimension.getFirst() * Math.tan(Math.toRadians(this.angle)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= 330 && this.angle <= 359) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(position.getFirst() + cannonDimension.getFirst(),
                                position.getSecond() + DIMENSION.getSecond() / 2
                                - (cannonDimension.getFirst() * Math.tan(Math.toRadians(360 - this.angle)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= 270 && this.angle < 330) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + cannonDimension.getFirst() / 2
                        + (DIMENSION.getSecond() * Math.tan(Math.toRadians(this.angle - 270))),
                        position.getSecond() - MARGINAL_DISTANCE), this.angle, projectileSpeed);
            } else {
                return new ProjectileImpl(
                        new Pair<Double, Double>(
                                position.getFirst() - cannonDimension.getFirst() / 2
                                + cannonDimension.getFirst() / 2 * Math.cos(Math.toRadians(this.angle)),
                                this.cannonPosition.getSecond()
                                + cannonDimension.getFirst() / 2 * Math.sin(Math.toRadians(this.angle))),
                        this.angle, projectileSpeed);
            }

        }
        
    }

}
