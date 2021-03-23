package com.example.android.mymovieapp.networking


import com.example.android.mymovieapp.responses.MoviesResponse
import com.example.android.mymovieapp.responses.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") q: String
    ): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>


    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") api_Key: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>

    @GET("tv/popular")
    fun getPopularTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TvShowResponse>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TvShowResponse>

    @GET("tv/on_the_air")
    fun getOnAirTvShows(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Call<TvShowResponse>

}