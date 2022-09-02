package com.hugoandrada.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.ItemMoviesBinding
import com.hugoandrada.movieapp.utils.AppConstants
import com.hugoandrada.movieapp.utils.Extensions.loadImage

class MainAdapter(private val movieClickListener: OnMovieClickListener
) : ListAdapter<Movie, MainAdapter.MainViewHolder>(MainDiffUtilCallback) {

    object MainDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    interface OnMovieClickListener {
        fun movieClicked(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            movieClickListener.movieClicked(movie)
        }
    }

    inner class MainViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movieCover.loadImage("${AppConstants.IMAGE_URL}${movie.poster_path}")
            binding.movieTitle.text = movie.title
            binding.movieOverview.text = movie.overview
        }
    }
}