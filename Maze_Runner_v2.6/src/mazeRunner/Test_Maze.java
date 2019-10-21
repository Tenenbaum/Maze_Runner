package mazeRunner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class Test_Maze {
	
	Maze maze;
	
	@BeforeEach
	void setUp() throws Exception {
		maze = new Maze(10);
	}
	
	@Test
	void testMaze() {		
		assertEquals(800, maze.getHeight());
		assertEquals(800, maze.getWidth());
		assertEquals(10, maze.rows);
		assertEquals(10, maze.cols);
		assertEquals(100, maze.tileMap.size());
	}

	@ParameterizedTest
	@ValueSource( ints = {10, 20, 40, 50})
	void testMazeResize(int size) {
		maze = new Maze(size);
		assertEquals(size, maze.rows);
		assertEquals(size, maze.cols);
	}

	@Test	
	void testGetMapIndex() {	
		assertEquals(-1, maze.getMapIndex(-1, 0));		
		assertEquals(-1, maze.getMapIndex(100, 50));	
		assertEquals(0, maze.getMapIndex(0, 0));
		assertEquals(81, maze.getMapIndex(1, 8));		
	}
	
	



}
