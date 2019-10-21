package mazeRunner;

import java.util.ArrayList;

/**
 * This class represents a two-dimensional grid of Tile objects, arranged in rows and columns.
 * @author Laura Egginton
 *
 */
public class Maze{
	
//		**********
//  	 Variables
//		**********

	
		int rows;
		int cols;
		int winMoves;
		Tile goal;
		ArrayList<Tile> tileMap;
		
		private int height = 800;
		private int width = 800;
		
		/**
		 * Constructs a symmetrical grid of Tile objects along a Cartesian co-ordinate system.
		 * @param size represents the number of rows and columns. Should not exceed 50 in a standard Maze.
		 */
		
//		**********
//		 Constructors
//		**********
		
		
//		this is for scaleability:  the size limit should be 50, as >50 makes the tiles too small
		public Maze(int size) {			
			this.rows = size;
			this.cols = size;

			this.tileMap = new ArrayList<Tile>();
			
			for(int i = 0; i< this.rows; i++) {
				for(int j=0; j<this.cols; j++) {
					this.tileMap.add(new Tile(j, i, this.height / this.rows, this.width / this.cols));
				}
			}
		};
		

		/**
		 * This method calculates the index position of a Tile within the ArrayList Maze.tileMap, according to its given x and y co-ordinates.
		 * @param row represents the x co-ordinate of a Tile, passed from Agent.getNeighbour
		 * @param col represents the y co-ordinate of a Tile, passed from Agent.getNeighbour
		 * @return
		 */
//		**********
//		 Methods
//		**********
		
//		calculate the 2D index within tileMap and check for invalid path  
		int getMapIndex(int row, int col) {
			
			if(row<0 || row>this.rows -1 ||col<0 || col>this.cols -1) {
				return -1;
			}
			else {
				return row + col * this.cols;
			}
			
		}
		
		/**
		 * This method provides the height of a Maze object
		 * @return the height value for this Maze
		 */
		int getHeight() {
			return this.height;
		}
		
		/**
		 * This method provides the width of a Maze object
		 * @return the width value for this Maze
		 */
		int getWidth() {
			return this.width;
		}
}
