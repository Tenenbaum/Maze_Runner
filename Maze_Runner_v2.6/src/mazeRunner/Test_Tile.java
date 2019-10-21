package mazeRunner;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Test_Tile {
	Tile tile; 	
	boolean [] testWalls;
	
	@Before
	public void setUp() {		
		tile = new Tile(0, 0, 10, 10);
		testWalls = new boolean [] {true, true, true, true};
	}	
	

	@Test
	public void testTile() {		
		assertEquals(0, tile.xCoords);
		assertEquals(0, tile.yCoords);
		assertEquals(10, tile.height);
		assertEquals(10, tile.width);
		assertEquals(false, tile.visited);
		assertEquals(1, tile.stepCost);
		assertArrayEquals(testWalls, tile.walls);		
	}

}
