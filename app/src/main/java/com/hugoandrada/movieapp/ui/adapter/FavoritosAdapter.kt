package com.hugoandrada.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.ItemMoviesBinding

class FavoritosAdapter(
    private val movieList: MutableList<Movie>,
    private val onMovieFavClick: OnMovieFavClickListener
) :
    RecyclerView.Adapter<FavoritosAdapter.FavViewHolder>() {

    interface OnMovieFavClickListener {
        fun onMovieClicked(movie: Movie, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie,position)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class FavViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, position: Int) {
            Glide.with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .centerCrop()
                .into(binding.movieCover)
            binding.movieTitle.text = movie.title
            binding.movieOverview.text = movie.overview

            binding.root.setOnClickListener {
                onMovieFavClick.onMovieClicked(movie, position)
                movieList.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }
}