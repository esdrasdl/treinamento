package br.unicamp.training.movieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.model.Movie;

public class PopularMovieAdapter extends BaseAdapter {

    private List<Movie> movies;
    private Context context;

    public PopularMovieAdapter(List<Movie> list, Context context) {
        movies = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.apply(getItem(position));
        return convertView;
    }

    class ViewHolder {
        ImageView cover;
        TextView title;

        ViewHolder(View view) {
            cover = (ImageView) view.findViewById(R.id.item_movie_cover);
            title = (TextView) view.findViewById(R.id.item_movie_title);
        }

        public void apply(Movie movie) {
            String posterURL = Movie.IMAGE_BASE_URL + movie.getBackdropPath();
            Picasso.with(context).load(posterURL).into(cover);
            title.setText(movie.getTitle());
        }
    }

}
