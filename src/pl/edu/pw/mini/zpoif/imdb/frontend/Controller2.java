/*
 * kontroler do sceny nr 2
 * 
 */

package pl.edu.pw.mini.zpoif.imdb.frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.edu.pw.mini.zpoif.imdb.backend.Film;
import pl.edu.pw.mini.zpoif.imdb.backend.FilmRequest;

public class Controller2 {

	private Scene scene;
	private Stage stage;
	private Parent root;

	@FXML
	private Label podobnyUno, podobnyDos, podobnyTres;

	@FXML
	private Label tytul;
	@FXML
	private Button przyciskSzukaj;
	@FXML
	private Button przyciskWroc;

	@FXML
	private Label napis, data, gatunek, rezyseria, scenariusz, aktorzy, fabula, ocena, typ;

	@FXML
	private ImageView naPlakat;

	@FXML
	private Label test1;

	@FXML
	private ImageView plakat2;

	public Controller2() {
	}

	public void zmieniaj(FilmRequest filmRequest) {

		Film myFilm = filmRequest.getFilm();
		if (myFilm == null) {

			tytul.setText("There isn't such film in database :((");

		} else {

			tytul.setText("" + myFilm.getTitle());
			tytul.setWrapText(true);
			data.setText("Date: " + myFilm.getReleased());
			gatunek.setText("Genre: " + myFilm.getGenre());
			rezyseria.setText("Director: " + myFilm.getDirector());
			scenariusz.setText("Script: " + myFilm.getWriter());
			aktorzy.setText("Actors: " + myFilm.getActors());
			fabula.setText("Plot: " + myFilm.getPlot());
			fabula.setWrapText(true);
			typ.setText("Type: " + myFilm.getTyp().name().toString());
			foto(myFilm);

			ArrayList<String> similarFilms = filmRequest.getSimilarFilms();
			int size = similarFilms.size();
			if (size <= 0) {
				return;
			}
			if (size <= 1) {
				podobnyUno.setText(similarFilms.get(1));
			}
			if (size <= 2) {
				podobnyUno.setText(similarFilms.get(1));
				podobnyDos.setText(similarFilms.get(2));
			}
			if (size >= 3) {
				podobnyUno.setText(similarFilms.get(1));
				podobnyDos.setText(similarFilms.get(2));
				podobnyTres.setText(similarFilms.get(3));
			}
		}
	}

	@FXML
	public void wroc() throws IOException {

		URL url = new File("src/resources/Main.fxml").toURI().toURL();
		root = FXMLLoader.load(url);
		stage = (Stage) przyciskWroc.getScene().getWindow();

		scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();

	}

	public void foto(Film myFilm) {
		InputStream input;
		try {
			input = new URL(myFilm.getPoster()).openStream();
			Image plakat = new Image(input);
			plakat2.setImage(plakat);
		} catch (IOException e) {
			System.out.println("chuj");
			e.printStackTrace();
		}

	}
}