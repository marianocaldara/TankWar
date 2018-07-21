package controller;

public interface GameLoop {
	
	 /**
     * Temporarily stops the loop until resume() is called.
     */
    void pauseLoop();

    /**
     * Makes the loop restart if it was previously paused.
     */
    void resumeLoop();

    /**
     * Stops the loop and the running thread.
     */
    void stopLoop();

}
