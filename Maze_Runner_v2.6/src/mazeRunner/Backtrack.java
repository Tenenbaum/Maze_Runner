package mazeRunner;

/**
 * This class uses a recursive backtracking method based on Kruskal's algorithm
 * to form long corridors pathways with short branches
 * @author Laura Egginton
 *
 */
public class Backtrack extends Mapmaker{
	
//		**********
//		 Variables
//		**********	
		
//		inherited from Mapmaker
	
	
//		**********
//		 Constructors
//		**********
	
		public Backtrack(Tile s) {
			super(s);			
		}
		
	
//		**********
//		 Methods
//		**********

		
		/**
		 * This method moves Mapmaker to the next valid Tile and adds the Tile's stepCost to this Mapmaker's own.
		 * This Mapmaker's total stepCost is measured and when it has visited half of all available Tiles, the Mapmaker's 
		 * currentTile is set to become the goal state. 
		 * If no valid Tiles are found, Mapmaker returns to its previous location and searches for a new Tile.
		 * When all available Tiles have been visited, Mapmaker is disabled.
		 */
		
		@Override
		void updateLocation(Maze m, int d) {
			this.totalSteps += this.currentTile.stepCost;
			this.currentTile.visited = true;			
			int n = this.getNeighbour(m, d);
			
			if(n > -1) {				
				this.next = this.neighbours.get(n);				
				this.visited.push(this.currentTile);
				this.removeWall(this.currentTile, this.next);
				this.currentTile = this.next;				
			}			
			else if(n < 0){
				this.currentTile = this.visited.pop();				
			}
			
			if(this.totalSteps == m.tileMap.size()/2) {
				m.goal = this.currentTile;
				m.winMoves = this.totalSteps;
			}
			
			if(this.visited.size() == 0) {//			
				this.finished = true;
			}				
		}
		


}
