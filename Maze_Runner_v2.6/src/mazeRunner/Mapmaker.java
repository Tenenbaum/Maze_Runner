package mazeRunner;

import java.util.Random;

/**
 * This class uses different random maze generation algorithms 
 * to construct a grid of connecting cells with the goal of reaching
 * a specific cell to exit the maze
 * 
 * @author Laura Egginton
 *
 */
public class Mapmaker extends Agent{
	
//		**********
//		 Variables
//		**********	
		
//		inherited from Agent
	
//		**********
//		 Constructors
//		**********
	
		public Mapmaker(Tile s) {
			super(s);			
		}
				
		
//		**********
//		 Methods
//		**********
		
		/**
		 * This method gets the location of all Tile objects neighbouring this Mapmaker that are within the bounds of a given Maze, 
		 * chooses one randomly from an ArrayList and returns its index, or -1 if no available Tiles are found.
		 */

		//overridden method must have equivalent args to inherited method
		@Override
		int getNeighbour(Maze m, int ri) {
//			prevent index out of bounds error
			this.neighbours.clear();
			
			int top = m.getMapIndex(this.currentTile.xCoords -1, this.currentTile.yCoords);
			int right = m.getMapIndex(this.currentTile.xCoords, this.currentTile.yCoords +1);
			int bottom = m.getMapIndex(this.currentTile.xCoords +1, this.currentTile.yCoords);
			int left = m.getMapIndex(this.currentTile.xCoords, this.currentTile.yCoords -1);
			
			if(top >-1 && !m.tileMap.get(top).visited) {
				this.neighbours.add(m.tileMap.get(top));
			}
			else if(right >-1 && !m.tileMap.get(right).visited) {
				this.neighbours.add(m.tileMap.get(right));
			}
			if(bottom >-1 && !m.tileMap.get(bottom).visited) {
				this.neighbours.add(m.tileMap.get(bottom));
			}
			else if(left >-1 && !m.tileMap.get(left).visited) {
				this.neighbours.add(m.tileMap.get(left));
			}
			
			
			Random r = new Random();			
//			only if valid
			if(this.neighbours.size() > 0) {			
				ri = r.nextInt(this.neighbours.size());					
			}

			return ri;	
			
		}
		
	
		
		/**
		 * This method compares two neighbouring Tiles and removes the connecting walls between them.
		 * Other Agents should not be permitted access, as it breaks the rules of the game.
		 * @param c this Mapmaker's current location
		 * @param n this Mapmaker's next location
		 */
		
//		Mapmaker only
		protected void removeWall(Tile c, Tile n) {
			int x = c.xCoords - n.xCoords;
			int y = c.yCoords - n.yCoords;			
			
			if(x == 1) {
				c.walls[3] = false;
				n.walls[1] = false;				
			}
			if(x == -1) {
				c.walls[1] = false;
				n.walls[3] = false;					
						}
			if(y == 1) {
				c.walls[0] = false;
				n.walls[2] = false;					
						}
			if(y == -1) {
				c.walls[2] = false;
				n.walls[0] = false;		
			}
		}
		
		

}
