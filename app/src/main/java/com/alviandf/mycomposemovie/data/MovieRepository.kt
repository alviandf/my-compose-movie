package com.alviandf.mycomposemovie.data

import com.alviandf.mycomposemovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    // Network
    suspend fun getPopularMovies(): Flow<Resource<List<Movie>>>
    suspend fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    suspend fun getSearchMovies(query: String): Flow<Resource<List<Movie>>>

    // Database
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun isFavoriteMovie(id: Int): Flow<Boolean>
    suspend fun insertFavoriteMovie(movie: Movie)
    suspend fun deleteFavoriteMovie(movie: Movie)
}