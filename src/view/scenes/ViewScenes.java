package view.scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import view.stage.GameWorldStage;
import view.stage.LoginStage;
import view.stage.MenuStage;
import view.stage.RegistrationStage;

/**
 * 
 * Implementation of the enum who regulates the scene switching
 *
 */
public enum ViewScenes {

    LOGIN {
        private SceneChanger log = new LoginStage();
        
        @Override
        public void setGameStage(ActionEvent event) throws IOException {
            log.setStage(event);
        }
    },
    SUBSCRIBTION {
        private SceneChanger sub = new RegistrationStage();

        @Override
        public void setGameStage(ActionEvent event) throws IOException {
            sub.setStage(event);
        }
    },
    MENU {
        private SceneChanger menu = new MenuStage();

        @Override
        public void setGameStage(ActionEvent event) throws IOException {
            menu.setStage(event);
        }
    },
    GAME_WORLD {
        private SceneChanger world = new GameWorldStage();

        @Override
        public void setGameStage(ActionEvent event) throws IOException {
            world.setStage(event);
        }
    };

	/**
	 * This method allow to switch to the desired stage
	 * @param event
	 * 			the action event.
	 * @throws IOException
	 */
    public abstract void setGameStage(ActionEvent event) throws IOException;

}
