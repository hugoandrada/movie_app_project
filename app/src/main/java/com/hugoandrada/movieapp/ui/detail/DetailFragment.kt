package com.hugoandrada.movieapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.databinding.FragmentDetailBinding
import com.hugoandrada.movieapp.presentation.LocalMovieViewModel
import com.hugoandrada.movieapp.utils.AppConstants
import com.hugoandrada.movieapp.utils.Extensions.loadImage
import com.hugoandrada.movieapp.utils.Extensions.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val localViewModel: LocalMovieViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setupBotonSaveFavorito()

        binding.moviePosterBack.loadImage("${AppConstants.IMAGE_URL}${args.backdropPath}")
        binding.moviePosterFront.loadImage("${AppConstants.IMAGE_URL}${args.posterPath}")

        binding.movieTitle.text = args.title
        binding.movieRating.text = "${args.voteAverage} ${AppConstants.RATING}"
        binding.movieComentarios.text = "${args.popularity} ${AppConstants.VOTES}"
        if (args.overview.isEmpty()) {
            binding.movieDescripcion.text = AppConstants.EMPTY_VIEW
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
            toast("${AppConstants.FAV_TOAST} ${args.title}")
        }
    }
}