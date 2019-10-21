package mazeRunner;

import java.util.Random;

/**
 * This class manages the interaction between View and Controller and maintains the current game state
 * @author Laura Egginton
 *
 */
public class Model {
	
	//**********
		// Variables
		//**********
		View view; 
		Controller controller;
		
		public Maze maze;
		Agent mapper;
		Player player;
		
		int mapSize;
		int width;
		int height;
		String mapType;
		
	/**
	 * Constructs a Model with height and width values and sets the initial number of rows/columns for Maze. 
	 * Note: as View is instantiated after Model, Maze cannot take user input for its constructor.	
	 */
		//**********
		// Constructors
		//**********
		public Model(int w, int h) {
			this.width = w;
			this.height = h;
			mapSize = 10;
			mapType = "Kruskal";
			newGame();
		}
		
		
		/**
		 * This method begins a new game when called.
		 */
		//**********
		// Methods
		//**********
		
		public void newGame() {
			
			Debug.trace("Model : newGame()");
			
			Random r = new Random();
			maze = new Maze(mapSize);
			
			if(mapType.equals("Kruskal")) {
				mapper = new Backtrack(maze.tileMap.get(r.nextInt(maze.tileMap.size()-1)));
			}else if(mapType.equals("Prim's")) {
				mapper = new Prim(maze.tileMap.get(r.nextInt(maze.tileMap.size()-1)));
			}
			
			player = new Player(maze.tileMap.get(r.nextInt(maze.tileMap.size()-1)));
			
			while(!mapper.finished) {
//				the -1 here indicates that mapper is requesting a direction and is considered an invalid argument by other Agents
				mapper.updateLocation(maze, -1);
			}

			
		}
		
		/**
		 * This method checks the state of the current Player and compares it to the given win conditions, 
		 * then updates View with the current game state.
		 */
		public void updateGame() {
			Debug.trace("Model : updateGame()");
			
			if(player.currentTile == maze.goal && player.totalSteps <= maze.winMoves) {
				player.finished = true;
				view.lblScoreInfo.setText("You won with " + player.totalSteps + " moves.");
			}
			else if(player.currentTile == maze.goal && player.totalSteps > maze.winMoves) {
				player.finished = true;
				view.lblScoreInfo.setText("You lost with " + player.totalSteps + " moves.");
			}
			else {
				view.lblScoreInfo.setText("Moves: " + player.totalSteps + "/" + maze.winMoves);
			}
			
			view.update();
		}
		
		/**
		 * This method closes the application when prompted.
		 */
		
		public void quit() {
			System.exit(0);
		}

}
