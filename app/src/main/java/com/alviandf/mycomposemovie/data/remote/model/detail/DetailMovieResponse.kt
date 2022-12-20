package com.alviandf.mycomposemovie.data.remote.model.detail

import com.alviandf.mycomposemovie.domain.model.Movie
import com.alviandf.mycomposemovie.utils.Constants
import com.alviandf.mycomposemovie.utils.emptyString
import com.alviandf.mycomposemovie.utils.orFalse
import com.alviandf.mycomposemovie.utils.orZero
import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any? = null,
    @SerializedName("budget")
    val budget: Int? = null,
    @SerializedName("genres")
    val genres: List<GenreResponse?>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imdb_id")
    val imdbId: String? = null,
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
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyResponse?>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryResponse?>? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("revenue")
    val revenue: Long? = null,
    @SerializedName("runtime")
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageResponse?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
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
            backdropPath = if(backdropPath.isNullOrEmpty()) emptyString() else Constants.BASE_IMAGE_URL + backdropPath,
            genreIds = genres?.map { it?.id.orZero() } ?: listOf(),
            id = id.orZero(),
            originalLanguage = originalLanguage.orEmpty(),
            originalTitle = originalTitle.orEmpty(),
            overview = overview.orEmpty(),
            popularity = popularity.orZero(),
            posterPath = if(posterPath.isNullOrEmpty()) emptyString() else Constants.BASE_IMAGE_URL + posterPath,
            releaseDate = releaseDate.orEmpty(),
            title = title.orEmpty(),
            video = video.orFalse(),
            voteAverage = voteAverage.orZero(),
            voteCount = voteCount.orZero()
        )
    }
}
