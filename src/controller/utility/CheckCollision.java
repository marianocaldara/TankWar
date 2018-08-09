package controller.utility;

import java.util.List;

import exceptions.ProjectileOutOfBordersException;
import exceptions.ProjectileWithProjectileException;
import exceptions.TankOutOfBordersException;
import exceptions.TankWithProjectileException;
import exceptions.TankWithTankException;
import model.Model;
import model.object.Projectile;
import model.object.Tank;

/**
 * Utility class that catch the collision between the game objects.
 */
public final class CheckCollision {

    private static Model world;

    /**
     * Constructor.
     */
    private CheckCollision() { }

    /**
     * Initialize the fields of the class.
     * 
     * @param model
     *            the {@link Model}.
     */
    public static void initialize(final Model model) {
        world = model;
    }

    /**
     * Manage the collision between the two {@link Tank}.
     *
     * @param playerTank
     *          the player {@link Tank}.
     * @param enemyTank
     *          the enemy {@link Tank}.
     *
     * @throws TankWithTankException
     *             if there is any collision.
     */
    public static void tankWithTank(final Tank playerTank, final Tank enemyTank) throws TankWithTankException {
        if (CheckIntersection.intersects(playerTank.getPosition(), playerTank.getDimension(), enemyTank.getPosition(),
                enemyTank.getDimension())) {
            throw new TankWithTankException();
        }
    }

    /**
     * Manage the collision between a {@link Tank} and a {@link Projectile}.
     * 
     * @param projectiles
     *            a {@link List} of projectiles.
     * @throws TankWithProjectileException
     *             if there is any collision.
     */
    public static void tankWithProjectile(final List<Projectile> projectiles) throws TankWithProjectileException {
        if (projectiles.stream()
                .anyMatch(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(),
                        world.getPlayer().getPosition(), world.getPlayer().getDimension()))
                || projectiles.stream().anyMatch(p -> CheckIntersection.intersects(p.getPosition(), p.getBounds(),
                        world.getEnemy().getPosition(), world.getEnemy().getDimension()))) {
            throw new TankWithProjectileException();
        }
    }

    /**
     * Manage the collision between the two {@link Tank} and the {@link World}
     * borders.
     * 
     * @param tank
     *          the colliding {@link Tank}.
     * @throws TankOutOfBordersException
     *             if the {@link Tank} goes out the {@link World} bounds.
     */
    public static void tankWithBorders(final Tank tank) throws TankOutOfBordersException {
        if (tank.getPosition().getFirst() < 0
                || tank.getPosition().getFirst() + tank.getDimension().getFirst() > world.getBounds().getFirst()
                || (tank.getPosition().getSecond() < 0 || tank.getPosition().getSecond()
                        + tank.getDimension().getSecond() > world.getBounds().getSecond())) {
            throw new TankOutOfBordersException();

        }
    }

    /**
     * Manage the collision between the {@link Projectile} and the {@link World}
     * borders.
     * 
     * @param projectile
     *            the colliding {@link Projectile}.
     * @throws ProjectileOutOfBordersException
     *             if the {@link Projectile} goes out the {@link World} bounds.
     */
    public static void projectileWithBorders(final Projectile projectile) throws ProjectileOutOfBordersException {
        if (projectile.getPosition().getFirst() + projectile.getBounds().getFirst() >= world.getBounds().getFirst()
                || projectile.getPosition().getFirst() <= 0 || projectile.getPosition().getSecond()
                        + projectile.getBounds().getSecond() >= world.getBounds().getSecond()
                || projectile.getPosition().getSecond() <= 0) {
            throw new ProjectileOutOfBordersException();

        }

    }

    /**
     * Manage the collision between two {@link Projectile}.
     * 
     * @param projectiles
     *            the list of {@link Projectile}.
     * @throws ProjectileWithProjectileException
     *             if there is any collision.
     */
    public static void projectileWithProjectile(final List<Projectile> projectiles) throws ProjectileWithProjectileException {
        for (Projectile p : projectiles) {
            for (Projectile x : projectiles) {
                if (CheckIntersection.intersects(p.getPosition(), p.getBounds(), x.getPosition(), x.getBounds())
                        && x != p) {
                    throw new ProjectileWithProjectileException();
                }
            }
        }
    }

}
