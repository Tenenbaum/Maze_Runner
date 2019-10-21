package mazeRunner;

import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
		@Override
		public void start(Stage window) {
			
			Debug.set(true);
			Debug.trace("MazeRunner initialising...");
			//window dimensions 
			int h = 800;
			int w = 900;	
			
			
			Model model = new Model(w,h);
	        View  view  = new View(w,h);
	        Controller controller  = new Controller();
	        
	        model.view = view;
	        model.controller = controller;
	        controller.model = model;
	        controller.view = view;
	        view.controller = controller;
	        view.model = model;
	        
	        view.Start(window);
	        
			Debug.trace("Main:finished");
		}


}
