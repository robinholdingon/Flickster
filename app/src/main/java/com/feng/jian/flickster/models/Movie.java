package com.feng.jian.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jian_feng on 3/27/17.
 */

public class Movie {
    String posterPath;
    String originalTitle;
    String overview;
    String backDropPath;
    ItemType.TYPE type;

    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backDropPath = jsonObject.getString("backdrop_path");
        this.type = Double.valueOf(jsonObject.getString("vote_average")) > 7.0 ?
                ItemType.TYPE.POPULAR : ItemType.TYPE.REGULAR;
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i ++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackDropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s", backDropPath);
    }

    public ItemType.TYPE getType() {
        return type;
    }
}
