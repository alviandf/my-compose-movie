package com.alviandf.mycomposemovie.data.local.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alviandf.mycomposemovie.data.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorites(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getFavorites(): Flow<List<MovieEntity>>

    @Delete
    fun deleteFavorites(movieEntity: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    fun isFavorite(id: Int): Flow<List<MovieEntity>>
}
