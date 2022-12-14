package com.alviandf.mycomposemovie.data.remote

import com.alviandf.mycomposemovie.data.remote.api.ApiClient
import com.alviandf.mycomposemovie.data.remote.api.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiClient: ApiClient) {

    suspend fun getPopularMovies() =
        flow {
            try {
                val response = apiClient.getPopularMovies().data
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailMovie(id: Int) =
        flow {
            try {
                emit(ApiResponse.Success(apiClient.getDetailMovie(id)))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getSearchMovies(query: String) =
        flow {
            try {
                val response = apiClient.getSearchMovies(query).data
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
}