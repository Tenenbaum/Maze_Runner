package mazeRunner;

//a note on JavaFX and Java 11 SDK: need to ensure JavaFX11 is installed in the Java root. 
//Add as User Library under modulepath (NOT CLASSPATH)
//If problems persist, add 'requires javafx.(packageName)' to module.info
//Issues appear to be due to an existing conflict between Eclipse and JavaFX

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class implements and manages the User Interface elements for MazeRunner
 * @author Laura Egginton
 *
 */
public class View implements EventHandler<KeyEvent>{
	
	//**********
	// Variables
	//**********
	
//	window dimensions
	int height;
	int width;

	GridPane pane;
	Canvas canvas;
	Label lblTitle;
	Label lblControl;
	Label lblInstruct;
	Label lblScoreInfo;
	Label txtControl;
	Label txtInstruct;
	Label lblDiff;
	ComboBox<Integer> cboxDifficulty;
	ComboBox<String> cBoxMazeType; 
	

	public Controller controller;
	public Model model;
	
	Maze maze;	
	Player player;
	
	//**********
	// Methods
	//**********
	
	/**
	 * This method instantiates View with the dimensions for width and height
	 * @param w the width of this View
	 * @param h the height of this View
	 */
	public View(int w, int h) {
		Debug.trace("View:<constructor>");
		width = w;
		height = h;
	}
	
	/**
	 * This method constructs the application window and its child UI nodes
	 * @param window represents the Stage object 
	 */
	
	public void Start(Stage window) {
		Debug.trace("View:Start()");
		
		pane = new GridPane();
		canvas = new Canvas(width, height);
		lblTitle = new Label("Maze Runner 2");
		lblControl = new Label("Controls");
		lblInstruct = new Label("How To Play");
		lblScoreInfo = new Label(" ");
		lblDiff = new Label("Choose maze size");
		txtControl = new Label("Use W, A, S, D or your arrow keys to move. \nUse R to start a new game. Press ESCAPE to quit.");
		txtInstruct = new Label("To win, reach the goal (green tile) before you run out of moves. \nIf you don't reach the goal before your moves run out, then you lose. ");
		cboxDifficulty = new ComboBox<Integer>();
		cboxDifficulty.getItems().addAll(10, 20, 40, 50);
		cboxDifficulty.getSelectionModel().selectFirst();
		cBoxMazeType = new ComboBox<String>();
		cBoxMazeType.getItems().addAll("Kruskal", "Prim's");
		cBoxMazeType.getSelectionModel().selectFirst();
	
//		manage layout
		pane.setPadding(new Insets(10,10,10,10));
		pane.setVgap(5);
		pane.setHgap(10);
		pane.setAlignment(Pos.CENTER);
		pane.setMinHeight(height);
		pane.setMinWidth(width);
		
//		CSS
		pane.setId("Maze_runner");
		pane.getStyleClass().add("pane");
		lblTitle.getStyleClass().add("label");
		lblControl.getStyleClass().add("label");
		lblInstruct.getStyleClass().add("label");
		lblScoreInfo.getStyleClass().add("label");
		lblDiff.getStyleClass().add("label");
		txtControl.getStyleClass().add("text");
		txtInstruct.getStyleClass().add("text");
		cboxDifficulty.getStyleClass().addAll("combo-box", "list-view", "combo-box-popup");
		cBoxMazeType.getStyleClass().addAll("combo-box", "list-view", "combo-box-popup");
	
//		position elements
		pane.add(lblTitle, 0, 0);	
		pane.add(lblControl, 0, 1);
		pane.add(txtControl, 0, 2);
		pane.add(lblInstruct, 0, 3);
		pane.add(txtInstruct, 0, 4);
		pane.add(lblDiff, 1, 1);
		pane.add(cboxDifficulty, 1, 2);
		pane.add(cBoxMazeType, 1, 3);
		pane.add(canvas, 1, 5 );
		pane.add(lblScoreInfo, 1, 4);
		
		
		
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("mazeRunner.css");
		scene.setUserAgentStylesheet("mazeRunner.css");

		scene.setOnKeyPressed(this);
		
		window.setTitle("MazeRunner version 2.2");
		window.setMinHeight(height);
		window.setMinWidth(width);
		window.setScene(scene);
		window.show();
		update();
	}
	
//	 note: you have to name this method 'handle' and specify public scope,
//	 or View doesn't count it as an implemented method and
//	 throws (error - non-abstract method)
	@Override
	public void handle(KeyEvent event) {
		Debug.trace("View:handle()");
		controller.usrKeys(event);
	}
	
	/**
	 * This method draws a grid of Rectangle and Stroke objects that represent the Maze
	 */
	
	void drawCanvas() {
		Debug.trace("View:draw()");
		
//		 note: canvas can have only one gc, but manages a stack of objects
		GraphicsContext gc = canvas.getGraphicsContext2D();		
		
		for(int i = 0; i<maze.tileMap.size(); i++) {
			displayObj(gc, maze.tileMap.get(i));
		}		
		
	}
	
	/**
	 * This method takes a given GameObj and draws a representative Shape on Canvas
	 * @param gc the GraphicsContext object used to render the Canvas
	 * @param obj the GameObj to be rendered
	 */
	
	void displayObj(GraphicsContext gc, GameObj obj) {
		int x = obj.xCoords;
		int y = obj.yCoords;
		
//		TODO: make this more generic with options for different shapes
		
//		assume it's a tile and downcast to <Tile>
		Tile t = (Tile)obj;		
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(3);
//		draw tile borders
		if(t.walls[0]) {
			gc.strokeLine(x*obj.width, y*obj.height, x*obj.width+obj.width, y*obj.height);
		}
		if(t.walls[1]) {
			gc.strokeLine(x*obj.width+obj.width, y*obj.height, x*obj.width+obj.width, y*obj.height+obj.height);
		}
		if(t.walls[2]) {
			gc.strokeLine(x*obj.width+obj.width, y*obj.height+obj.height, x*obj.width, y*obj.height+obj.height);			
				}
		if(t.walls[3]) {
			gc.strokeLine(x*obj.width, y*obj.height+obj.height, x*obj.width, y*obj.height);
			
		}
		
		if(t == player.currentTile) {
			gc.setFill( Color.PURPLE );	
		}
		else if(t == maze.goal && t != player.currentTile) {
			gc.setFill(Color.GREEN);
		}
		else if(t.visited) {
			gc.setFill( Color.RED );		
			
		}		
		
		gc.fillRect( x*obj.width, y*obj.height, obj.width, obj.height );
	};
	
	/**
	 * This method updates the Canvas to match the current game state
	 */
	
	public void update() {
		Debug.trace("View : update()");
		maze = model.maze;
		player = model.player;
		
		drawCanvas();
	}
	
	
	
	
}
