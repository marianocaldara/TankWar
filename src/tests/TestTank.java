package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import model.Model;
import model.World;
import model.utility.Pair;

/**
 * JUnit Test for the Tanks.
 */
public class TestTank {

    /**
     * Tests the fields of a new tank where it's possible.
     */
    @Test
    public void testInitialStateTank() {
        final Model world = new World();
        world.configPlayerTank(new Pair<>(0.0, 0.0), 3);
        world.configEnemyTank(new Pair<>(100.0, 100.0), 3, 3, 4);
        assertNotNull(world.getPlayer());
        assertNotNull(world.getEnemy());
        assertSame(world.getPlayer().getLifes(), 3);
        assertSame(world.getEnemy().getLifes(), 3);
        assertSame(world.getPlayer().getPosition().getFirst().intValue(), 0);
        assertSame(world.getPlayer().getPosition().getSecond().intValue(), 0);
        assertSame(world.getEnemy().getPosition().getFirst().intValue(), 100);
        assertSame(world.getEnemy().getPosition().getSecond().intValue(), 100);
    }

    /**
     * Tests the position of the two tanks.
     */
    @Test
    public void testTankPosition() {
        final Model world = new World();
        world.configPlayerTank(new Pair<>(0.0, 0.0), 3);
        world.configEnemyTank(new Pair<>(100.0, 100.0), 3, 3, 4);
        world.getPlayer().setPosition(new Pair<>(20.0, 20.0));
        world.getEnemy().setPosition(new Pair<>(300.0, 300.0));
        assertSame(world.getPlayer().getPosition().getFirst().intValue(), 20);
        assertSame(world.getPlayer().getPosition().getSecond().intValue(), 20);
        assertSame(world.getEnemy().getPosition().getFirst().intValue(), 300);
        assertSame(world.getEnemy().getPosition().getSecond().intValue(), 300);
    }

    /**
     * Tests if the two tanks is alive.
     */
    @Test
    public void testTankDead() {
        final Model world = new World();
        world.configPlayerTank(new Pair<>(0.0, 0.0), 3);
        world.configEnemyTank(new Pair<>(100.0, 100.0), 3, 3, 4);
        assertTrue(world.getPlayer().isAlive());
        assertTrue(world.getEnemy().isAlive());
        world.getPlayer().damage(3);
        world.getEnemy().damage(3);
        assertFalse(world.getPlayer().isAlive());
        assertFalse(world.getEnemy().isAlive());
    }
}
