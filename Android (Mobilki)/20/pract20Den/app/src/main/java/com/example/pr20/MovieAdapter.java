package com.example.kinopoiskapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieResponse> movies;
    private Context context;

    public MovieAdapter(Context context, List<MovieResponse> movies) {
        this.context = context;
        this.movies = movies;
    }

    public void updateMovies(List<MovieResponse> newMovies) {
        this.movies = newMovies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(com.example.kinopoiskapi.R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieResponse movie = movies.get(position);
        String title = movie.getNameOriginal() != null ? movie.getNameOriginal() : movie.getNameRu();
        holder.nameOriginal.setText(title);
        holder.year.setText("Год: " + movie.getYear());
        holder.rating.setText("Рейтинг: " + movie.getRating());

        StringBuilder genresText = new StringBuilder();
        for (Genre genre : movie.getGenres()) {
            genresText.append(genre.getGenre()).append(", ");
        }
        if (genresText.length() > 0) genresText.setLength(genresText.length() - 2);
        holder.genres.setText("Жанры: " + genresText.toString());

        Glide.with(context)
                .load(movie.getPosterUrl())
                .into(holder.poster);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView nameOriginal, year, rating, genres;
        ImageView poster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOriginal = itemView.findViewById(R.id.textTitle);
            year = itemView.findViewById(R.id.textYear);
            rating = itemView.findViewById(R.id.textRating);
            genres = itemView.findViewById(R.id.textGenres);
            poster = itemView.findViewById(R.id.imagePoster);
        }
    }
}