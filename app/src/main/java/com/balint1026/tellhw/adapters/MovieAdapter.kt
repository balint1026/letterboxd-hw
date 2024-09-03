package com.balint1026.tellhw.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balint1026.tellhw.databinding.ItemMovieBinding
import com.balint1026.tellhw.models.Movie
import com.bumptech.glide.Glide


class MovieAdapter(
    private val movies: List<Movie>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding) {
            Glide.with(moviePoster.context)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(moviePoster)

            root.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }

    override fun getItemCount(): Int = movies.size
}