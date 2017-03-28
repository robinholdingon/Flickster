package com.feng.jian.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feng.jian.flickster.R;
import com.feng.jian.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jian_feng on 3/27/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private int orientation;

    public static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies, int orientation) {
        super(context, android.R.layout.simple_list_item_1, movies);
        this.orientation = orientation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        Movie movie = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder();

            ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

            // clear out image from last time
            ivImage.setImageResource(0);

            TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

            viewHolder.ivImage = ivImage;
            viewHolder.tvTitle = tvTitle;
            viewHolder.tvOverview = tvOverview;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(getImagePathForOrientation(orientation, movie)).into(viewHolder.ivImage);
        return convertView;
    }

    private String getImagePathForOrientation(int orientation, Movie movie) {
        return orientation == Configuration.ORIENTATION_PORTRAIT ? movie.getPosterPath() : movie.getBackDropPath();
    }
}
