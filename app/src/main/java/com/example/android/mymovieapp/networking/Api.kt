package com.example.android.mymovieapp.networking


import com.example.android.mymovieapp.responses.MoviesResponse
import com.example.android.mymovieapp.responses.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") q: String
    ): MoviesResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ):  MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):  MoviesResponse


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_Key: String,
        @Query("page") page: Int
    ):  MoviesResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):  TvShowResponse

    @GET("tv/top_rated")
    suspend  fun getTopRatedTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):  TvShowResponse

    @GET("tv/on_the_air")
    suspend fun getOnAirTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ):  TvShowResponse

}