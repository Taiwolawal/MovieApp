package com.example.android.mymovieapp.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class TvShow(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("first_air_date") val firstAirDate: String
)
