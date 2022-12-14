package com.alviandf.mycomposemovie.data.remote.model

import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.utils.Constants.BASE_IMAGE_URL
import com.alviandf.mycomposemovie.utils.orEmptyList
import com.alviandf.mycomposemovie.utils.orFalse
import com.alviandf.mycomposemovie.utils.orZero
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
) {

    fun toMovieDomain(): Movie {
        return Movie(
            adult = adult.orFalse(),
            backdropPath = BASE_IMAGE_URL + backdropPath,
            genreIds = genreIds.orEmptyList(),
            id = id.orZero(),
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            overview = overview.orEmpty(),
            popularity = popularity.orZero(),
            posterPath = BASE_IMAGE_URL + posterPath,
            releaseDate = releaseDate.orEmpty(),
            title = title.orEmpty(),
            video = video.orFalse(),
            voteAverage = voteAverage.orZero(),
            voteCount = voteCount.orZero()
        )
    }
}
