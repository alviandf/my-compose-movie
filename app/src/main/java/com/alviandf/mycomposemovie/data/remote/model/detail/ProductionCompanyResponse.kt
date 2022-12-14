package com.alviandf.mycomposemovie.data.remote.model.detail


import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("logo_path")
    val logoPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("origin_country")
    val originCountry: String? = null
)