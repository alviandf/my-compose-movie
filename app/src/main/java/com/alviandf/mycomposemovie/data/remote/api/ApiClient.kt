package com.alviandf.mycomposemovie.data.remote.api

import com.alviandf.mycomposemovie.data.remote.model.MovieResponse
import com.alviandf.mycomposemovie.data.remote.model.ResultResponse
import com.alviandf.mycomposemovie.data.remote.model.detail.DetailMovieResponse
import com.alviandf.mycomposemovie.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularMovies(): ResultResponse<List<MovieResponse>>

    @GET("movie/{id}?api_key=$API_KEY&language=en-US")
    suspend fun getDetailMovie(@Path("id") id: Int): DetailMovieResponse

    @GET("search/movie?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getSearchMovies(@Query("query") query: String): ResultResponse<List<MovieResponse>>
}