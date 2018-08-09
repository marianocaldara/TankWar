package controller.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.collision.FactoryCollision;
import controller.output.ControllerOutput;
import controller.output.ControllerOutputImpl;
import controller.utility.CheckCollision;
import controller.utility.Convertitor;
import exceptions.ProjectileOutOfBordersException;
import exceptions.ProjectileWithProjectileException;
import exceptions.TankOutOfBordersException;
import exceptions.TankWithProjectileException;
import exceptions.TankWithTankException;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.input.Input;
import model.object.Projectile;
import model.object.Tank;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;

/**
 * Concrete implementation of the interfaces {@link ControllerTank} and
 * {@link ControllerProjectile}. This class control all the main objects of the
 * game and connect the {@link View} to the {@link Model}.
 */
public class ControllerObjects implements ControllerTank, ControllerProjectile {

    private static final double MIN_DISTANCE_TO_SHOT = 350;
    private static final double MIN_DISTANCE = 70;
    private final Tank playerTank;
    private final Tank enemyTank;
    private final Input playerInput;
    private List<Projectile> projectiles;
    private double timeToShot;
    private long initialTime;
    private long finalTime;
    private FactoryCollision factoryCollision;
    private ControllerOutput controllerOutput;

    /**
     * Constructor.
     * @param factoryCollision
     *            a factory class to create a {@link Collision}.
     * @param playerTank
     *            the player {@link Tank}.
     * @param enemyTank
     *            the enemy {@link Tank}.
     * @param playerInput
     *            the player {@link Input}.
     * @param timeToShot
     *            the the time in ms between two enemy shots.
     */
    public ControllerObjects(final FactoryCollision factoryCollision, final Tank playerTank, final Tank enemyTank,
            final Input playerInput, final double timeToShot) {
        this.playerTank = playerTank;
        this.enemyTank = enemyTank;
        this.playerInput = playerInput;
        this.timeToShot = timeToShot;
        this.projectiles = new ArrayList<>();
        this.factoryCollision = factoryCollision;
        this.controllerOutput = new ControllerOutputImpl(this.projectiles, this.playerTank, this.enemyTank);
    }

    /**
     * Getter of the {@link ControllerOutput}.
     * @return the controller output.
     */
    public final ControllerOutput getControllerOutput() {
        return this.controllerOutput;
    }

    @Override
    public final void playerShot(final MouseEvent event) {
        switch (event.getButton()) {
        case PRIMARY:
            this.projectiles.add(this.playerTank.shot());
            break;
        default:
        }

    }

    @Override
    public final void updateProjectiles() {
        this.projectiles.forEach(p -> p.update());
        this.checkProjectileCollision();
        this.deleteProjectiles(this.getDeadProjectiles());
    }

    @Override
    public final void movePlayerTank(final KeyEvent event, final boolean b) {
        switch (event.getCode()) {
        case UP:
            this.playerInput.getMovement().put(Direction.UP, b);
            break;
        case DOWN:
            this.playerInput.getMovement().put(Direction.DOWN, b);
            break;
        case LEFT:
            this.playerInput.getMovement().put(Direction.LEFT, b);
            break;
        case RIGHT:
            this.playerInput.getMovement().put(Direction.RIGHT, b);
            break;
        case W:
            this.playerInput.getMovement().put(Direction.UP, b);
            break;
        case S:
            this.playerInput.getMovement().put(Direction.DOWN, b);
            break;
        case A:
            this.playerInput.getMovement().put(Direction.LEFT, b);
            break;
        case D:
            this.playerInput.getMovement().put(Direction.RIGHT, b);
            break;
        default:
        }

    }

    @Override
    public final void updateTank() {
        this.playerTank.update(this.playerInput);
        this.enemyTank.update(AI.act(this.enemyTank, this.playerTank, this.getNearestProjectiles()));
        if (this.finalTime - this.initialTime > this.timeToShot && Calculate.distance(this.playerTank.getPosition(),
                this.enemyTank.getPosition()) < MIN_DISTANCE_TO_SHOT) {
            this.initialTime = System.currentTimeMillis();
            this.projectiles.add(AI.shotEnemy(this.enemyTank)); // da sistemare
        }
        this.finalTime = System.currentTimeMillis();
        this.checkTankCollision();
    }

    @Override
    public final void movePlayerCannon(final MouseEvent event) {
        this.playerInput
                .setTarget(Convertitor.viewToModel(new Pair<Double, Double>(event.getSceneX(), event.getSceneY())));

    }

    /**
     * Getter of the list of dead projectiles.
     * 
     * @return the list of dead {@link Projectile}.
     */
    private List<Projectile> getDeadProjectiles() {
        return this.projectiles.stream().filter(p -> !p.isAlive()).collect(Collectors.toList());
    }

    /**
     * Delete all the dead projectiles from the map.
     * 
     * @param deadProjectiles
     *            the list of dead {@link Projectile}.
     */
    private void deleteProjectiles(final List<Projectile> deadProjectiles) {
        this.projectiles.removeAll(deadProjectiles);
    }

    /**
     * Getter of all the {@link Projectile} nearest to the enemy {@link Tank}.
     * 
     * @return the {@link List} of the nearest projectiles.
     */
    private List<Projectile> getNearestProjectiles() {
        return this.projectiles.stream().filter(p -> (int) Calculate.distance(p.getPosition(),
                new Pair<>(this.enemyTank.getPosition().getFirst() + this.enemyTank.getDimension().getFirst() / 2,
                        this.enemyTank.getPosition().getSecond()
                                + this.enemyTank.getDimension().getSecond() / 2)) < MIN_DISTANCE)
                .collect(Collectors.toList());

    }

    private void checkTankCollision() {
        try {
            CheckCollision.tankWithBorders(this.playerTank);
        } catch (TankOutOfBordersException e) {
            this.factoryCollision.tankWithBordersCollision(this.playerTank).manageCollision();
        }
        try {
            CheckCollision.tankWithBorders(this.enemyTank);
        } catch (TankOutOfBordersException e) {
            this.factoryCollision.tankWithBordersCollision(this.enemyTank).manageCollision();
        }
        try {
            CheckCollision.tankWithTank(this.playerTank, this.enemyTank);
        } catch (TankWithTankException e) {
            this.factoryCollision.tankWithTankCollision(this.playerTank, this.enemyTank, this.playerInput.getMovement())
                    .manageCollision();
        }

    }

    private void checkProjectileCollision() {
        this.projectiles.forEach(p -> {
            try {
                CheckCollision.projectileWithBorders(p);
            } catch (ProjectileOutOfBordersException e) {
                this.factoryCollision.projectileWithBordersCollision(p).manageCollision();
            }
        });
        try {
            CheckCollision.tankWithProjectile(projectiles);
        } catch (TankWithProjectileException e) {
            this.factoryCollision.tankWithProjectileCollision(this.playerTank, this.enemyTank, this.projectiles)
                    .manageCollision();
        }
        try {
            CheckCollision.projectileWithProjectile(projectiles);
        } catch (ProjectileWithProjectileException e) {
            this.factoryCollision.projectileWithProjectileCollision(this.projectiles).manageCollision();
        }
    }

}
