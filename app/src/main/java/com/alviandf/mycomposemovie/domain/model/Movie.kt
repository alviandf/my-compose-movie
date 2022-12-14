package com.alviandf.mycomposemovie.domain.model

import android.os.Parcelable
import com.alviandf.mycomposemovie.data.local.model.MovieEntity
import com.alviandf.mycomposemovie.utils.emptyString
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean = false,
    val backdropPath: String = emptyString(),
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    val originalLanguage: String = emptyString(),
    val originalTitle: String = emptyString(),
    val overview: String = emptyString(),
    val popularity: Double = 0.0,
    val posterPath: String = emptyString(),
    val releaseDate: String = emptyString(),
    val title: String = emptyString(),
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable {

    fun toMovieEntity(): MovieEntity {
        return MovieEntity(
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}
