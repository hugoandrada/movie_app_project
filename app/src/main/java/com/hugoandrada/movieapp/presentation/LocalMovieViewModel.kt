package com.hugoandrada.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.hugoandrada.movieapp.data.local.MovieEntity
import com.hugoandrada.movieapp.repo.local.RepoLocal
import com.hugoandrada.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalMovieViewModel
@Inject constructor(private val repoLocal: RepoLocal) : ViewModel() {

    fun fetchLocalMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repoLocal.getFavoritos()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun saveLocalMovie(movieEntity: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repoLocal.saveFavoritos(movieEntity)
        }
    }

    fun deleteLocalMovie(movieEntity: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repoLocal.deleteFavoritos(movieEntity)
        }
    }
}