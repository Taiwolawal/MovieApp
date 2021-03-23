package com.example.android.mymovieapp.responses

import com.example.android.mymovieapp.model.Movies
import com.google.gson.annotations.SerializedName

class MoviesResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Movies>,
    @SerializedName("total_pages") val pages: Int
)