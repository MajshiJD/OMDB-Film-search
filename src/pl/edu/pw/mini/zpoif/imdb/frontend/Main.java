/*
 * Aplikacja typu 'filmweb'. Która umożliwia wyszukiwnia filmów i seriali.
 * Oprócz podstawowych informacji o filmie zwracany jest również plakat oraz podobne tytuły.
 */


package pl.edu.pw.mini.zpoif.imdb.frontend;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/resources/Main.fxml").toURI().toURL();
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Filmweb 2.0");
			primaryStage.show();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
