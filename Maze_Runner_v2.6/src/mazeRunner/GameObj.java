package mazeRunner;

/**
 * This class implements a generic two-dimensional GameObj object
 * @author Laura Egginton
 *
 */
public class GameObj {
	
	int xCoords;
	int yCoords;
	int height;
	int width;
	
	
	public GameObj() {
		
	}
	
	/**
	 * 
	 * @param x the x co-ordinates for this GameObj
	 * @param y the y co-ordinates for this GameObj
	 * @param h the height of this GameObj
	 * @param w the width of this GameObj
	 */
	public GameObj(int x, int y, int h, int w) {
		this.xCoords = x;
		this.yCoords = y;
		this.height = h;
		this.width = w;
		
	}
	
}


