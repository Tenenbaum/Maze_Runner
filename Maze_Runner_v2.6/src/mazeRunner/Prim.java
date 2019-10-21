package mazeRunner;

import java.util.Random;

/**
 * This class uses a modified and randomised Prim's algorithm to remove random walls
 * @author Laura Egginton
 *
 */
public class Prim extends Mapmaker{
	
//		**********
//		 Variables
//		**********	
		
//		inherited from Mapmaker
	
//		**********
//		 Constructors
//		**********
	
		public Prim(Tile s) {
			super(s);			
		}
		

//		**********
//		 Methods
//		**********

		
		
		@Override
		void updateLocation(Maze m, int d) {
			Random rn = new Random();
			this.totalSteps += this.currentTile.stepCost;
			if(!this.currentTile.visited) {
				this.currentTile.visited = true;
				this.visited.push(this.currentTile);

			}
			int n = this.getNeighbour(m, d);
			
			if(n > -1) {				
					this.next = this.neighbours.get(n);				
					this.removeWall(this.currentTile, this.next);
					this.currentTile = this.next;		
			}
			else if(n < 0){
				this.currentTile = m.tileMap.get(rn.nextInt(m.tileMap.size()-1));
			}
			
			if(this.totalSteps == m.tileMap.size()/2) {
				m.goal = this.currentTile;
				m.winMoves = this.totalSteps;
			}
			
			if(this.visited.size() == m.tileMap.size()) {		
				this.finished = true;
			}				
		}
	


}
