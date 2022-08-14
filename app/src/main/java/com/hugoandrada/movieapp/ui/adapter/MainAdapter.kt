package com.hugoandrada.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.ItemMoviesBinding

class MainAdapter(private val movieList: List<Movie>,
                  private val movieClickListener: OnMovieClickListener) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface OnMovieClickListener{
        fun movieClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            movieClickListener.movieClicked(movie)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MainViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: Movie){
                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .centerCrop()
                    .into(binding.movieCover)
                binding.movieTitle.text = movie.title
                binding.movieOverview.text = movie.overview
            }
    }
}