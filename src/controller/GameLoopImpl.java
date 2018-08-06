package controller;

import java.io.IOException;

import javafx.application.Platform;
import view.View;

/**
 * Concrete implementation of the {@link GameLoop} interface.
 * This class allows to manage the game loop.
 */
public class GameLoopImpl extends Thread implements GameLoop{	
	private static final long MS_BETWEEN_FRAMES = 20;
	private volatile boolean paused;
	private volatile boolean stopped;
	private Controller controller;
	private View view;
	
	public GameLoopImpl(Controller controller, View view) {
		this.controller = controller;
		this.view = view;
	}
	
	public void run() {
        while (!this.stopped){
            if (this.paused) {
                synchronized (this) {
                    while (this.paused) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) { }
                    }
                }
            }
            final long current = System.currentTimeMillis();
            this.processInput();
            Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					updateGame();
					
				}
			});
            Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
				  render();
					
				}
			});
            this.waitForNextFrame(current);
        }
    }

    public synchronized void pauseLoop() {
        this.paused = true;
    }

    public synchronized void resumeLoop() {
        this.paused = false;
        this.notifyAll();
    }

    public synchronized void stopLoop() {
        this.stopped = true;
         this.interrupt();
    }
    
    /**
     * Method used to process the input of the player.
     */
    private void processInput() {
    	try {
			this.view.getGameWorldController().moveTank();
			this.view.getGameWorldController().moveCannon();
			this.view.getGameWorldController().shot();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Method used to update the game according to the input.
     */
    private void updateGame() {
        this.controller.getControllerObjects().updateTank();
        this.controller.getControllerObjects().updateProjectiles();
        this.updateLevelState();
    }

    /**
     * Method used to redraw the view after the game updating.
     */
    private void render() {
       try {
		view.getGameWorldController().updateView();
	} catch (IOException e) {
		e.printStackTrace();
		}	
    }

    /**
     * Method used to make the thread sleep waiting the next frame.
     * @param current
     * 		the current time in ms.
     */
    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < MS_BETWEEN_FRAMES) {
            try {
                Thread.sleep(MS_BETWEEN_FRAMES - dt);
            } catch (InterruptedException ex) { }
        }
    }
    
    private void updateLevelState() {
		if(!this.controller.getControllerObjects().isPlayerAlive()) {
			this.controller.getLevel().setGameOver();
		}
		else if(!this.controller.getControllerObjects().isEnemyAlive()) {
			this.controller.getLevel().setLevelEnded();
		}
	}

}
