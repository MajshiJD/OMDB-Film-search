package pl.edu.pw.mini.zpoif.imdb.backend;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilmRequest {
	private boolean response;
	private String jsonStr, requestLine, jsonSearchStr;
	private ArrayList<String> similarFilms;

	public FilmRequest(String requestLine) {
		super();
		this.requestLine = requestLine;
		this.jsonSearchStr = readData(getConnection(requestLine, false));
		this.similarFilms = createSimilarFilms(jsonSearchStr);
		JSONObject jsonObj = new JSONObject(jsonSearchStr);
		this.response = (Boolean) jsonObj.getBoolean("Response");

		if (response) {
			String mainFilm = similarFilms.get(0).split(" - ")[0];

			String[] splited = mainFilm.split("\\s+");

			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < splited.length; i++) {
				String line = splited[i];
				sb.append(line + "+");

			}

			String mainFilmRequest = sb.toString();
			this.jsonStr = readData(getConnection(mainFilmRequest, true));
		}

	}

	private static HttpURLConnection getConnection(String line, boolean isSearchByTitle) {
		// key - e1396a1a
		URL url = null;
		String requestType;

		if (isSearchByTitle) {
			requestType = "t=";
		} else {
			requestType = "s=";
		}

		try {
			url = new URL("https://www.omdbapi.com/?" + requestType + line + "&apikey=e1396a1a");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpURLConnection connection = null;

		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private static String readData(HttpURLConnection connection) {
		InputStream inStream = null;
		try {
			inStream = connection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (Scanner in = new Scanner(inStream, "UTF-8")) {
			StringBuilder sb = new StringBuilder("");
			while (in.hasNext()) {
				String line = in.next();
				sb.append(line + " ");
			}
			return sb.toString();
		}
	}

	private static ArrayList<String> createSimilarFilms(String jsonStr) {
		JSONObject jobj = new JSONObject(jsonStr);
		if (!jobj.getBoolean("Response")) {
			return null;
		}

		JSONArray filmsJsonList = jobj.getJSONArray("Search");
		ArrayList<String> filmTitles = new ArrayList<String>();

		for (int i = 0; i < 6; i++) {
			JSONObject tmpJson = new JSONObject(filmsJsonList.get(i).toString());
			filmTitles.add(tmpJson.getString("Title") + " - " + tmpJson.getString("Year"));
		}

		return filmTitles;

	}

	public String getJsonStr() {
		return jsonStr;
	}

	public Film getFilm() {
		if (response) {
			return new Film(jsonStr);
		}
		return null;
	}

	public ArrayList<String> getSimilarFilms() {
		if (response) {
			similarFilms.remove(0);
			return similarFilms;
		}
		return null;

	}

}