package controller.loader;

import java.lang.reflect.InvocationTargetException;

import controller.levels.Levels;

/**
 * Interface to read and write on files.
 */
public interface FileController {

    /**
     * Method to read a level state from file.
     * 
     * @throws InvocationTargetException
     */
    void loadLevel(Levels level);

    /**
     * Method to save a level state on file.
     */
    // void saveLevel();

}
