package com.example.ekok.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ekok.flixster.R;
import com.example.ekok.flixster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by ekok on 6/15/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        ImageView image;
        TextView title;
        TextView overview;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int orientation = getContext().getResources().getConfiguration().orientation;

        // get data item for position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        // check existing view being reused
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

        //clear out image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        //populate data

        if (orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            Picasso.with(getContext()).load(movie.getPosterPath()).transform(new RoundedCornersTransformation(15, 15))
                    .placeholder(R.drawable.load)
                    .error(R.drawable.alert)
                    .into(ivImage);
        }
        else
        {
            Picasso.with(getContext()).load(movie.getBannerPath()).transform(new RoundedCornersTransformation(15, 15))
                    .placeholder(R.drawable.load)
                    .error(R.drawable.alert)
                    .into(ivImage);
        }

        viewHolder.overview.setText(movie.getOverview());
        viewHolder.title.setText(movie.getOriginalTitle());

        //return the view
        return convertView;
    }
}
