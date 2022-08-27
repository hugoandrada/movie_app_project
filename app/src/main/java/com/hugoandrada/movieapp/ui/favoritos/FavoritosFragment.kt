package com.hugoandrada.movieapp.ui.favoritos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.hugoandrada.movieapp.R
import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.data.model.Movie
import com.hugoandrada.movieapp.databinding.FragmentFavoritosBinding
import com.hugoandrada.movieapp.presentation.LocalMovieViewModel
import com.hugoandrada.movieapp.ui.adapter.FavoritosAdapter
import com.hugoandrada.movieapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritosFragment : Fragment(R.layout.fragment_favoritos),
    FavoritosAdapter.OnMovieFavClickListener {

    private lateinit var binding: FragmentFavoritosBinding
    private val localViewModel: LocalMovieViewModel by viewModels()
    private lateinit var favoritosAdapter: FavoritosAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritosBinding.bind(view)
        favoritosAdapter = FavoritosAdapter(mutableListOf(), this)
        setupRvFavoritos()
        setupObserver()
    }

    private fun setupObserver() {
        localViewModel.fetchLocalMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (result.data.results.isEmpty()) {
                        binding.emptyView.visibility = View.VISIBLE
                    } else {
                        binding.rvFavoritos.adapter =
                            FavoritosAdapter(result.data.results, this)
                    }
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setupRvFavoritos() {
        binding.rvFavoritos.apply {
            adapter = favoritosAdapter
        }
    }

    override fun onMovieClicked(movie: Movie, position: Int) {
        localViewModel.deleteLocalMovie(
            MovieEntity(
                movie.id,
                movie.title,
                movie.poster_path,
                movie.backdrop_path,
                movie.popularity,
                movie.vote_average,
                movie.overview
            )
        )
        Toast.makeText(
            requireContext(),
            "se elimino de favoritos ${movie.title}",
            Toast.LENGTH_SHORT
        ).show()
    }
}