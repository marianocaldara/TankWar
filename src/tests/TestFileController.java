package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import controller.levels.Levels;
import controller.loader.FileController;
import controller.loader.FileControllerImpl;
import model.Model;
import model.World;

/**
 * Test class for the file controller.
 */
public class TestFileController {

    /**
     * Test the initial configuration read by the file controller.
     */
    @Test
    public void testFileControllerConfiguration() {
        final Model world = new World();
        final FileController file = new FileControllerImpl(world);
        file.loadLevel(Levels.LEVEL_1);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), 14);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), 536);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getPlayer().getLifes(), 5);
        assertEquals(world.getEnemy().getLifes(), 5);
        file.loadLevel(Levels.LEVEL_2);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), 14);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), 536);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getPlayer().getLifes(), 4);
        assertEquals(world.getEnemy().getLifes(), 5);
        file.loadLevel(Levels.LEVEL_3);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), 14);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), 536);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getPlayer().getLifes(), 3);
        assertEquals(world.getEnemy().getLifes(), 5);
        file.loadLevel(Levels.LEVEL_4);
        assertEquals(world.getPlayer().getPosition().getFirst().intValue(), 14);
        assertEquals(world.getPlayer().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getEnemy().getPosition().getFirst().intValue(), 536);
        assertEquals(world.getEnemy().getPosition().getSecond().intValue(), 175);
        assertEquals(world.getPlayer().getLifes(), 2);
        assertEquals(world.getEnemy().getLifes(), 5);
    }

}
