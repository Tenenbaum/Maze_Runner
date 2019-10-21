package mazeRunner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Test_Mapmaker {

	Maze m;
	Backtrack mapper;
	
	
	@BeforeEach
	void setUp() throws Exception {
		m = new Maze(10);
		mapper = new Backtrack(m.tileMap.get(0));
	}
	

	@Test
	void testUpdateLocation() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNeighbour() {
		fail("Not yet implemented");
	}

}
