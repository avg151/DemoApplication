package com.example.data.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("culture")
    val culture: String
)