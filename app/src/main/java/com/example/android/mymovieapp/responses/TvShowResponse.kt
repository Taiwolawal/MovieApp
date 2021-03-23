package com.example.android.mymovieapp.responses

import com.example.android.mymovieapp.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowResponse(@SerializedName("page") val page: Int,
                          @SerializedName("results") val tvShows: List<TvShow>,
                          @SerializedName("total_pages") val pages: Int)
