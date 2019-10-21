package mazeRunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Test_Agent {
	
	Maze m;
	Tile t;
	Agent a;	

	@BeforeEach
	void setUp() throws Exception {
		m = new Maze(10);
		t = m.tileMap.get(0);
		m.tileMap.get(0).walls[1] = false;
		m.tileMap.get(0).walls[2] = false;
		m.tileMap.get(1).walls[3] = false;
		m.tileMap.get(10).walls[0] = false;
		a = new Agent(t);		
	}

	@Test
	void testAgent() {
		assertEquals(t, a.start);
		assertEquals(t, a.currentTile);
		assertEquals(0, a.neighbours.size());
		assertEquals(0, a.visited.size());
		assertEquals(0, a.totalSteps);
		assertEquals(false, a.finished);
		assertNull(a.next);
	}

	@ParameterizedTest
	@ValueSource( ints = {1, 2})
	void testUpdateLocationValid(int dir) {
		a.updateLocation(m, dir);
		assertNotNull(a.next);
		assertEquals(1, a.totalSteps);
		assertEquals(1, a.neighbours.size());
		assertEquals(false, a.finished);
	}
	
	@ParameterizedTest
	@ValueSource( ints = {0, 3, -1, 5})
	void testUpdateLocationInvalid(int dir) {
		a.updateLocation(m, dir);
		assertNull(a.next);
		assertEquals(0, a.totalSteps);
		assertEquals(0, a.neighbours.size());
		assertEquals(false, a.finished);
	}


	@ParameterizedTest
	@ValueSource( ints = {1, 2})
	void testGetNeighbourValidDir(int dir) {
		assertNotEquals(-1, a.getNeighbour(m, dir));
	}
	
	@ParameterizedTest
	@ValueSource( ints = {0, 3, -1, 5})
	void testGetNeighbourInvalidDir(int dir) {
		assertEquals(-1, a.getNeighbour(m, dir));
	}

}
