package com.alviandf.mycomposemovie.data.remote.model

import com.google.gson.annotations.SerializedName

data class ResultResponse<T>(
    @field:SerializedName("results")
    val data: T
)
