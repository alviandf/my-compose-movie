package com.alviandf.mycomposemovie.data

import com.alviandf.mycomposemovie.data.local.LocalDataSource
import com.alviandf.mycomposemovie.data.remote.RemoteDataSource
import com.alviandf.mycomposemovie.data.remote.api.ApiResponse
import com.alviandf.mycomposemovie.data.remote.model.MovieResponse
import com.alviandf.mycomposemovie.data.remote.model.detail.DetailMovieResponse
import com.alviandf.mycomposemovie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MovieRepository {

    override suspend fun getPopularMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun load(data: List<MovieResponse>): Flow<List<Movie>> {
                return listOf(data.map { it.toMovieDomain() }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getPopularMovies()
            }
        }.asFlow()
    }

    override suspend fun getDetailMovie(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, DetailMovieResponse>() {
            override suspend fun load(data: DetailMovieResponse): Flow<Movie> {
                return listOf(data.toMovieDomain()).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(id)
            }
        }.asFlow()
    }

    override suspend fun getSearchMovies(query: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override suspend fun load(data: List<MovieResponse>): Flow<List<Movie>> {
                return listOf(data.map { it.toMovieDomain() }).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getSearchMovies(query)
            }
        }.asFlow()
    }

    override suspend fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavorites().map {
            it.map { entity ->
                entity.toMovieDomain()
            }
        }
    }

    override suspend fun isFavoriteMovie(id: Int): Flow<Boolean> {
        return localDataSource.isFavorite(id)
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        localDataSource.insertFavorite(movie.toMovieEntity())
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        localDataSource.deleteFavorite(movie.toMovieEntity())
    }
}