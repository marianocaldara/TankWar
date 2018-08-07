package view.scene;

import java.io.IOException;

import controller.Controller;
import view.stage.EndingGameStage;
import view.stage.EndingLevelStage;
import view.stage.GameWorldStage;
import view.stage.LoadingStage;
import view.stage.LoseStage;
import view.stage.MenuStage;
import view.stage.SettingStage;

/**
 * 
 * Implementation of the enumeration that regulates the scene switching.
 *
 */
public enum ViewScenes {

    MENU {
        private SceneChanger menu = new MenuStage();
        
        @Override
        public void setGameStage(double width, double height, Controller controller) throws IOException {
            menu.setStage(width, height, controller);
        }

		@Override
		public SceneChanger getGameStage() throws IOException {
			return menu;
		}

		
    },
    
    SETTING{
    	private SceneChanger setting = new SettingStage();
		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			setting.setStage(width, height, controller);
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			return setting;
		}
    	
    },
    
    LOADING{
    	
    	private SceneChanger loader = new LoadingStage();

		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			loader.setStage(width, height, controller);
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			return loader;
		}
    	
    },
    
    GAME_WORLD {
     
    	private SceneChanger world = new GameWorldStage();
		@Override
		public void setGameStage(double width, double height, Controller controller) throws IOException {
			world.setStage(width, height, controller);
		}
		@Override
		public SceneChanger getGameStage() throws IOException {
			return world;
			
		}
    },
    
    LOSE {

    	private SceneChanger gameOver = new LoseStage();
		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			gameOver.setStage(width, height, controller);
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			return gameOver;
		}
    	
    },
    
    END_LEVEL{

    	private SceneChanger endLevel = new EndingLevelStage();
    	
		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			endLevel.setStage(width, height, controller);
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			return endLevel;
		}
    	
    },
    
    END_GAME{

    	private SceneChanger endGame = new EndingGameStage();	
    	
		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			endGame.setStage(width, height, controller);
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			return endGame;
		}
    	
    };

	/**
	 * This method allow to switch to the desired stage
	 * @param event
	 * 			the action event.
	 * @throws IOException
	 */
    public abstract void setGameStage(double width, double height, Controller controller) throws IOException;
    
    /**
     * Getter of the game stage.
     * @return the current game stage.
     * @throws IOException
     */
    public abstract SceneChanger getGameStage() throws IOException;
    
    

}
