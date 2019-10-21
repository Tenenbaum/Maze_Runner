package mazeRunner;

/**
 * This class implements a two-dimensional Tile object that can be added to a 2d grid and resized to fit. 
 * It has four walls for collision detection, and provides values for progress tracking via movement cost and visitation.
 * @author Laura Egginton
 *
 */
public class Tile extends GameObj{
	
//	    **********
//		 Variables
//		**********
			
		
//		 TODO: make a tile that teleports Player to another tile. (Edit: a job for Mapmaker)
	
		int stepCost;		
		boolean [] walls = new boolean[] {true, true, true, true};
		boolean visited;
		
		/**
		 * Constructs a Tile object at (x, y), with width(w) and height(h).
		 * @param x the horizontal index
		 * @param y the vertical index
		 * @param h the height of the Tile
		 * @param w the width of the Tile
		 */
		
//		
//		**********
//		 Constructors
//		**********
		
		public Tile(int x, int y, int h, int w) {
			this.xCoords = x;
			this.yCoords = y;
			this.height = h;
			this.width = w;
			this.stepCost = 1;
			this.visited = false;			
		};
		
				
//		**********
//		 Methods
//		**********		
		
		//no methods

}
