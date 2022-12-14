package com.alviandf.mycomposemovie.domain

import com.alviandf.mycomposemovie.data.Resource
import com.alviandf.mycomposemovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    // Network
    suspend fun getPopularMovie(): Flow<Resource<List<Movie>>>
    suspend fun getDetailMovie(id: Int): Flow<Resource<Movie>>
    suspend fun getSearchMovie(query: String): Flow<Resource<List<Movie>>>

    // Database
    suspend fun insertFavoriteMovie(movie: Movie)
    suspend fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun deleteFavoriteMovie(movie: Movie)
    suspend fun isFavoriteMovie(id: Int): Flow<Boolean>
}