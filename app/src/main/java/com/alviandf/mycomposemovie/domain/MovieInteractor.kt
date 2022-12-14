package com.alviandf.mycomposemovie.domain

import com.alviandf.mycomposemovie.data.MovieRepository
import com.alviandf.mycomposemovie.data.Resource
import com.alviandf.mycomposemovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepository) : MovieUseCase {

    override suspend fun getPopularMovie(): Flow<Resource<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override suspend fun getDetailMovie(id: Int): Flow<Resource<Movie>> {
        return movieRepository.getDetailMovie(id)
    }

    override suspend fun getSearchMovie(query: String): Flow<Resource<List<Movie>>> {
        return movieRepository.getSearchMovies(query)
    }

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    override suspend fun isFavoriteMovie(id: Int): Flow<Boolean> {
        return movieRepository.isFavoriteMovie(id)
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        return movieRepository.insertFavoriteMovie(movie)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        return movieRepository.deleteFavoriteMovie(movie)
    }
}