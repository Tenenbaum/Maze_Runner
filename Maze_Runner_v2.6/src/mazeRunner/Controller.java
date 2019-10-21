package mazeRunner;

import javafx.scene.input.KeyEvent;

/**
 * This class manages the user input for Model and updates the game when recognised commands are issued.
 * @author Laura Egginton
 *
 */
public class Controller {
	
	//**********
	// Variables
	//**********
	
	public Model model;
	public View view;
	

	/**
	 * This method takes a KeyPress event and handles the response for Model. 
	 * The recognised input is WASD and arrow keys for Player movement, R for starting a new game and ESC to close the application MazeRunner.
	 */
	//**********
	// Methods
	//**********
	

	public void usrKeys(KeyEvent event) {

		//for keycodes see: https://docs.oracle.com/javafx/2/api/javafx/scene/input/KeyCode.html
		switch(event.getCode()) {
		//agent movement
		case UP:
		case W:
			model.player.updateLocation(model.maze, 0);			
			break;
		case DOWN:
		case S:
			model.player.updateLocation(model.maze, 2);
			break;
        case RIGHT:
        case D:
        	model.player.updateLocation(model.maze, 1);
			break;
        case LEFT:
        case A:
        	model.player.updateLocation(model.maze, 3);
			break;
		//quit
        case ESCAPE:
        	Debug.trace("MazeRunner: terminated");
        	model.quit();
        	break;
        //reset current maze
        case R:
        	model.mapSize = view.cboxDifficulty.getValue();
        	model.mapType = view.cBoxMazeType.getValue();
        	model.newGame();        	
        	break;
        //hint
        case H:
        	
        	break;
        //no keybinding
        default: 
        	
        	break;		
		}
		model.updateGame();
		
	}


}
