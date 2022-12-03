package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Apartment;
import model.Managment;
import model.Utilities;
import view.View;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Managment management = new Managment();
			View view = new View(primaryStage);
			Controller controller = new Controller(management , view);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}

	

