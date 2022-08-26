package com.hugoandrada.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.FragmentHomeBinding
import com.hugoandrada.movieapp.presentation.MovieViewModel
import com.hugoandrada.movieapp.ui.adapter.MainAdapter
import com.hugoandrada.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MainAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setupRv()
        setupObserver()
    }

    private fun setupRv() {
        binding.rvHome.adapter = MainAdapter(listOf(), this)
    }

    private fun setupObserver() {
        viewModel.fetchMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvHome.adapter = MainAdapter(result.data.results, this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        "Ocurrio un error al traer los datos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun movieClicked(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            movie.title,
            movie.poster_path,
            movie.backdrop_path,
            movie.popularity,
            movie.vote_average,
            movie.overview
        )
        findNavController().navigate(action)
    }
}