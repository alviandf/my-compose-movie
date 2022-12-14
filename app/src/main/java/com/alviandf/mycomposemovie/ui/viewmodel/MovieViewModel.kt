package com.alviandf.mycomposemovie.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alviandf.mycomposemovie.data.Resource
import com.alviandf.mycomposemovie.domain.MovieUseCase
import com.alviandf.mycomposemovie.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val useCase: MovieUseCase) : ViewModel() {

    private val _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>> get() = _movies

    private val _detailMovie = MutableLiveData<Resource<Movie>>()
    val detailMovie: LiveData<Resource<Movie>> get() = _detailMovie

    var favoriteMovies by mutableStateOf(emptyList<Movie>())
    var isFavorite by mutableStateOf(false)

    fun getPopularMovies() {
        viewModelScope.launch {
            useCase.getPopularMovie().collect {
                _movies.value = it
            }
        }
    }

    fun getSearch(query: String) {
        viewModelScope.launch {
            useCase.getSearchMovie(query).collect {
                _movies.value = it
            }
        }
    }

    fun getDetail(id: Int) {
        viewModelScope.launch {
            useCase.getDetailMovie(id).collect {
                _detailMovie.value = it
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch {
            useCase.getFavoriteMovies().collect {
                favoriteMovies = it
            }
        }
    }

    fun checkFavorite(id: Int) {
        viewModelScope.launch {
            useCase.isFavoriteMovie(id).collect {
                isFavorite = it
            }
        }
    }

    fun insertFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.insertFavoriteMovie(movie)
        }
    }

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteFavoriteMovie(movie)
        }
    }
}