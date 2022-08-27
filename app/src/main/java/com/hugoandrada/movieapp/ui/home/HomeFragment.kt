package com.hugoandrada.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.FragmentHomeBinding
import com.hugoandrada.movieapp.presentation.MovieViewModel
import com.hugoandrada.movieapp.ui.adapter.MainAdapter
import com.hugoandrada.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    MainAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mainAdapter = MainAdapter(listOf(), this)
        setupRv()
        setupObserver()
        setupBtnFav()
    }

    private fun setupBtnFav() {
        binding.btnFavoritos.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavoritosFragment())
        }
    }

    private fun setupRv() {
        binding.rvHome.apply {
            adapter = mainAdapter
        }
    }

    private fun setupObserver() {
        movieViewModel.fetchMovies().observe(viewLifecycleOwner, Observer { result ->
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
            movie.overview,
            movie.id
        )
        findNavController().navigate(action)
    }
}