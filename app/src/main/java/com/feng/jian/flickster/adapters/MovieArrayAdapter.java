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
import com.feng.jian.flickster.models.ItemType;
import com.feng.jian.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jian_feng on 3/27/17.
 */

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private int orientation;

    public static class ViewHolder {
        ImageView ivImage;
    }

    public static class RegularViewHolder extends ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
    }

    public static class PopularViewHolder extends ViewHolder{
    }

    public MovieArrayAdapter(Context context, List<Movie> movies, int orientation) {
        super(context, android.R.layout.simple_list_item_1, movies);
        this.orientation = orientation;
    }

    @Override
    public int getViewTypeCount() {
        return ItemType.TYPE.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().ordinal();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RegularViewHolder regularViewHolder;
        Movie movie = getItem(position);

        convertView = inflateViewForType(getItemViewType(position), convertView, parent);

        setupView(getItemViewType(position), (ViewHolder) convertView.getTag(), movie);
        return convertView;
    }

    private String getImagePathForOrientation(int orientation, Movie movie, int type) {
        return orientation == Configuration.ORIENTATION_LANDSCAPE || type == ItemType.TYPE.POPULAR.ordinal() ?
                movie.getBackDropPath() : movie.getPosterPath();
    }

    private View inflateViewForType(int type, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (type == ItemType.TYPE.POPULAR.ordinal()) {
                convertView = inflater.inflate(R.layout.item_movie_backdrop, parent, false);
                PopularViewHolder popularViewHolder = new PopularViewHolder();
                ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivBackdrop);
                ivImage.setImageResource(0);

                popularViewHolder.ivImage = ivImage;
                convertView.setTag(popularViewHolder);
            } else if (type == ItemType.TYPE.REGULAR.ordinal()) {
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
                RegularViewHolder regularViewHolder = new RegularViewHolder();

                ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

                // clear out image from last time
                ivImage.setImageResource(0);

                TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

                regularViewHolder.ivImage = ivImage;
                regularViewHolder.tvTitle = tvTitle;
                regularViewHolder.tvOverview = tvOverview;
                convertView.setTag(regularViewHolder);
            }
        }
        return convertView;
    }

    private void setupView(int type, ViewHolder viewHolder, Movie movie) {
        Picasso.with(getContext()).load(getImagePathForOrientation(orientation, movie, type))
                .placeholder(R.drawable.placeholder).fit().centerCrop().into(viewHolder.ivImage);

        if (type == ItemType.TYPE.REGULAR.ordinal()) {
            ((RegularViewHolder) viewHolder).tvTitle.setText(movie.getOriginalTitle());
            ((RegularViewHolder) viewHolder).tvOverview.setText(movie.getOverview());
        }
    }
}
