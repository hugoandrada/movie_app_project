package com.hugoandrada.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.backdropPath}")
            .centerCrop().into(binding.moviePosterDetail)
        Glide.with(requireContext())
            .load("https://image.tmdb.org/t/p/w500/${args.posterPath}")
            .centerCrop().into(binding.movieCoverDetail)

        binding.movieTitleDetail.text = args.title
        binding.movieVoteDetail.text = "${args.voteAverage} Rating."
        binding.moviePopularityDetail.text = "${args.popularity} Calificaciones."
        if (args.overview.isEmpty()) {
            binding.movieOverviewDetail.text = "No contiene informacion."
        } else {
            binding.movieOverviewDetail.text = args.overview
        }

    }
}