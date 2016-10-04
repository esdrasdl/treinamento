package br.unicamp.training.movieapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.unicamp.training.movieapp.R;
import br.unicamp.training.movieapp.model.PopularMovie;

public class PopularMovieAdapter extends BaseAdapter {

    private PopularMovie[] movies;
    private Context context;

    public PopularMovieAdapter(PopularMovie[] list, Context context) {
        movies = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.length;
    }

    @Override
    public PopularMovie getItem(int position) {
        return movies[position];
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

        public void apply(PopularMovie movie) {
            String posterURL = PopularMovie.IMAGE_BASE_URL + movie.getBackdropPath();
            Picasso.with(context).load(posterURL).into(cover);
            title.setText(movie.getTitle());
        }
    }

}
