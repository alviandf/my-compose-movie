package com.alviandf.mycomposemovie.data.local

import com.alviandf.mycomposemovie.data.local.database.MovieDao
import com.alviandf.mycomposemovie.data.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavorites(): Flow<List<MovieEntity>> {
        return movieDao.getFavorites()
    }

    fun isFavorite(id: Int): Flow<Boolean> {
        return movieDao.isFavorite(id).map { it.isNotEmpty() }
    }

    fun insertFavorite(movieEntity: MovieEntity) {
        movieDao.insertFavorites(movieEntity)
    }

    fun deleteFavorite(movieEntity: MovieEntity) {
        movieDao.deleteFavorites(movieEntity)
    }
}