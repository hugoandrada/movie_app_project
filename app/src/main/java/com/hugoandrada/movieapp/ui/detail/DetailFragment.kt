package com.hugoandrada.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.databinding.FragmentDetailBinding
import com.hugoandrada.movieapp.presentation.LocalMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val localViewModel: LocalMovieViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setupBotonSaveFavorito()

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backdropPath}")
            .centerCrop().into(binding.moviePosterBack)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.posterPath}")
            .centerCrop().into(binding.moviePosterFront)

        binding.movieTitle.text = args.title
        binding.movieRating.text = "${args.voteAverage} Rating."
        binding.movieComentarios.text = "${args.popularity} Calificaciones."
        if (args.overview.isEmpty()) {
            binding.movieDescripcion.text = "No contiene informacion."
        } else {
            binding.movieDescripcion.text = args.overview
        }

    }

    private fun setupBotonSaveFavorito() {
        binding.btnSaveFav.setOnClickListener {
            localViewModel.saveLocalMovie(
                MovieEntity(
                    args.id,
                    args.title,
                    args.posterPath,
                    args.backdropPath,
                    args.popularity,
                    args.voteAverage,
                    args.overview
                )
            )
            Snackbar.make(
                binding.root,
                "se guardo en favoritos ${args.title}",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }
}