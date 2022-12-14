package com.alviandf.mycomposemovie.data.remote.model.detail


import com.google.gson.annotations.SerializedName

data class SpokenLanguageResponse(
    @SerializedName("english_name")
    val englishName: String? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("name")
    val name: String? = null
)