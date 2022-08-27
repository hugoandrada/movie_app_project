package com.hugoandrada.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hugoandrada.movieapp.repo.remote.MovieRepository
import com.hugoandrada.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repo: MovieRepository) : ViewModel() {

    fun fetchMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}