package controller;

import javafx.application.Platform;
import view.ViewMain;

public class GameLoopImpl extends Thread implements GameLoop {
	private static final long MS_FRAMES = 20;
	private volatile boolean paused;
    private volatile boolean stopped;
    private Controller controller;
    private ViewMain view;
    
    public GameLoopImpl(Controller controller, ViewMain view) {
		this.controller = controller;
		this.view = view;
	}
	
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!this.stopped) {
            if (this.paused) {
                synchronized (this) {
                    while (this.paused) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) { }
                    }
                }
                lastTime = System.currentTimeMillis() - MS_FRAMES;
            }
            if(controller.getControllerObject().getPlayerLifes() == 0 || controller.getControllerObject().getEnemyLifes() == 0 ) {
            	stopLoop();
            }
            final long current = System.currentTimeMillis();
            this.processInput();
            this.updateGame();
            Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					render();
					
				}
			});
            this.waitForNextFrame(current);
            lastTime = current;
        }
    }

	@Override
	public synchronized void pauseLoop() {
		this.paused = true;

	}

	@Override
	public synchronized void resumeLoop() {
		 this.paused = false;
		 this.notifyAll();
	}

	@Override
	public synchronized void stopLoop() {
		 this.stopped = true;
	     this.interrupt();

	}
	
	private void processInput() {
	       //view method to process the input.
	}

	private void updateGame() {
		this.controller.getControllerObject().updateTank();
	    this.controller.getControllerObject().updateProjectiles();
	}

	private void render() {
	    this.view.gwc.updateTanksPos();
	}

	private void waitForNextFrame(final long current) {
	    final long dt = System.currentTimeMillis() - current;
	    if (dt < MS_FRAMES) {
	        try {
	            Thread.sleep(MS_FRAMES - dt);
	        } catch (InterruptedException ex) { }
	    }
	}

}
