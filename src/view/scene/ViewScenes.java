package view.scene;

import java.io.IOException;

import controller.Controller;
import view.stage.GameWorldStage;
import view.stage.MenuStage;

/**
 * 
 * Implementation of the enum who regulates the scene switching
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
    	//private SceneChanger setting = new SettingStage();
		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
    	
    },
    
    LOADING{

		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			// TODO Auto-generated method stub
			return null;
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

		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
    	
    },
    
    END_LEVEL{

		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
    	
    },
    
    END_GAME{

		@Override
		public void setGameStage(double width, double height, Controller controller)
				throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public SceneChanger getGameStage() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}
    	
    };

	/**
	 * This method allow to switch to the desired stage
	 * @param event
	 * 			the action event.
	 * @throws IOException
	 */
    public abstract void setGameStage(double width, double height, Controller controller) throws IOException;
    
    public abstract SceneChanger getGameStage() throws IOException;
    
    

}
