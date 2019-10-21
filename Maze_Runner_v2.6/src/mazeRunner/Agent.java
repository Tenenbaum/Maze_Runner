package mazeRunner;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements an Agent that traverses a two-dimensional grid in the form of a Maze object.
 * It can move up, down, left and right, but cannot move past walls.
 * An Agent can be disabled when its job is finished, which is when it has reached its goal state.
 * @author Laura Egginton
 *
 */
public class Agent {

	
//	**********
//	 Variables
//	**********
	
	int totalSteps;
	Tile currentTile;
	Tile start;
	Tile next;
	ArrayList <Tile> neighbours;
	Stack <Tile> visited;
//	use finished to disable an agent
	boolean finished;

	/**
	 * Instantiates an Agent with a given Tile object for its initial state values
	 * @param start the Tile object indicating this Agent's starting position
	 */

//	**********
//	 Constructors
//	**********
	
	public Agent(Tile start) {
		//initial state (start location)
		this.start = start;	
		this.currentTile = start;
		this.neighbours = new ArrayList<Tile>();
		this.visited = new Stack<Tile>();
		this.totalSteps = 0;
		this.finished = false;
	};


	/**
	 * This method moves an active Agent from its current location to the next accessible Tile, according to the given direction.
	 * If an indicated Tile is inaccessible, the Agent will not be moved.
	 * @param m the Maze object containing all possible locations
	 * @param d the integer value indicating the direction the Agent intends to move
	 */
//	**********
//	 Methods
//	**********
	
	void updateLocation(Maze m, int d) {
		if(!this.finished) {
			
			Debug.trace(this.getClass() + " : updateLocation()");		
			
			int n = this.getNeighbour(m, d);
			
			if(n > -1) {				
				this.next = this.neighbours.get(n);			
				this.currentTile = this.next;
				this.totalSteps += this.currentTile.stepCost;

			}			
			else if(n <0){				
				Debug.trace("Code "+ d + " is an invalid path.");
				
			}			
		}
		else {
			Debug.trace(this.getClass() + " has finished.");
		}
		
	}
	
	
	/**
	 * This method takes the Agent's intended direction and determines whether the from its current location is permitted.
	 * Only horizontal and vertical movement is allowed.
	 * @param m the Maze object passed from Agent.updateLocation
	 * @param dir the direction passed from Agent.updateLocation
	 * @return the position of a valid destination Tile within Agent.neighbours, or -1 if the destination is invalid.
	 */
	int getNeighbour(Maze m, int dir) {
		Debug.trace(this.getClass() + " : getNeighbour()");
		
//		prevent index out of bounds error
		this.neighbours.clear();
	
		int top, right, bottom, left;
		int ri = -1;

	
		switch(dir) {
		case 0:
			top = m.getMapIndex(this.currentTile.xCoords, this.currentTile.yCoords  -1);

			if(top >-1 && !this.currentTile.walls[0]) {
				this.neighbours.add(m.tileMap.get(top));
			}
			break;
		case 1:
			right = m.getMapIndex(this.currentTile.xCoords +1, this.currentTile.yCoords);

			if(right >-1 && !this.currentTile.walls[1]) {
				this.neighbours.add(m.tileMap.get(right));
			}
			break;
		case 2:
			bottom = m.getMapIndex(this.currentTile.xCoords, this.currentTile.yCoords +1);

			if(bottom >-1 && !this.currentTile.walls[2]) {
				this.neighbours.add(m.tileMap.get(bottom));
			}
			break;
		case 3:
			left = m.getMapIndex(this.currentTile.xCoords -1, this.currentTile.yCoords);

			if(left >-1 && !this.currentTile.walls[3]) {
				this.neighbours.add(m.tileMap.get(left));
			}
			break;
		default: 
			Debug.trace(dir + " is not a recognised direction.");
			break;
		}
		
//		only if valid
		if(this.neighbours.size() > 0) {			
			ri = 0;					
		}

		return ri;			
	}	
}
