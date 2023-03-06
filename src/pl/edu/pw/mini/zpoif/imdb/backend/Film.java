package pl.edu.pw.mini.zpoif.imdb.backend;

import org.json.JSONObject;

public class Film {

	private String title, poster, released, genre, director, writer, actors, plot;
	private double imdbRating;

	public enum type {
		movie, series
	};

	private type typ;
	private String jsonStr;

	public Film(String jsonStr) {
		super();
		this.jsonStr = jsonStr;
		JSONObject jobj = new JSONObject(jsonStr);
		// System.out.println( jobj.getJSONArray("Search"));
		this.director = (String) jobj.getString("Director");
		this.title = (String) jobj.getString("Title");
		this.plot = (String) jobj.getString("Plot");
		this.released = (String) jobj.getString("Released");
		this.imdbRating = (Double) jobj.getDouble("imdbRating");
		this.actors = (String) jobj.getString("Actors");
		this.genre = (String) jobj.getString("Genre");
		this.writer = (String) jobj.getString("Writer");
		this.typ = jobj.getEnum(type.class, "Type");
		this.poster = jobj.getString("Poster");
	}

	public String getTitle() {
		return title;
	}

	public String getPoster() {
		return poster;
	}

	public String getReleased() {
		return released;
	}

	public String getGenre() {
		return genre;
	}

	public String getDirector() {
		return director;
	}

	public String getWriter() {
		return writer;
	}

	public String getActors() {
		return actors;
	}

	public String getPlot() {
		return plot;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public type getTyp() {
		return typ;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	@Override
	public String toString() {
		return "TO JEST " + typ + ": [title=" + title + ", released=" + released + ", genre=" + genre + ", director="
				+ director + ", writer=" + writer + ", actors=" + actors + ", imdbRating=" + imdbRating;
	}

}