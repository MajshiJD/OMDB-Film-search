/*
 * kontroler do sceny nr 1
 * 
 */



package pl.edu.pw.mini.zpoif.imdb.frontend;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.imdb.backend.Film;
import pl.edu.pw.mini.zpoif.imdb.backend.FilmRequest;

public class Controller {

	private Scene scene;
	private Stage stage;
	private Parent root;

	@FXML
	private TextField poleTekstowe;

	@FXML
	private Label napis, tytul, data, gatunek, rezyseria, scenariusz, aktorzy, fabula, ocena, typ;

	@FXML
	private Button przyciskSzukaj;

	@FXML
	private Button przyciskWroc;
	private String wprowadzonyTytul;

	public void szukaj2() {

		String wprowadzonyTytul = poleTekstowe.getText();
		FilmRequest filmRequest = new FilmRequest(wprowadzonyTytul);
		Film myFilm = filmRequest.getFilm();
		// System.out.println(wprowadzonyTytul);
		URL url;
		try {
			url = new File("src/resources/Main2.fxml").toURI().toURL();
			FXMLLoader loader = new FXMLLoader(url);
			root = loader.load();
			Controller2 kontroler = loader.getController();
			kontroler.zmieniaj(filmRequest);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// FXMLLoader loader = new
		// FXMLLoader(getClass().getClassLoader().getResource("resources/Main2.fxml"));

		// System.out.println(loader);

		stage = (Stage) przyciskSzukaj.getScene().getWindow();
		scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void wroc() throws IOException {

		URL url = new File("src/Main.fxml").toURI().toURL();
		root = FXMLLoader.load(url);
		stage = (Stage) przyciskWroc.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}