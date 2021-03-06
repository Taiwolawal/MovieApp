package com.example.android.mymovieapp.networking

import com.example.android.mymovieapp.model.TvShow
import com.example.android.mymovieapp.util.API_KEY
import com.example.android.mymovieapp.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TvShowRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
       api = retrofit.create(Api::class.java)
    }

    suspend fun getPopularTvShows(page: Int = 1): List<TvShow> {
        val popularTvShows = api.getPopularTvShows(API_KEY, page)
       return popularTvShows.tvShows
    }

    suspend fun getTopRatedTvShows(page: Int = 1): List<TvShow> {
        val topRatedTvShow = api.getTopRatedTvShows(API_KEY, page)
        return topRatedTvShow.tvShows
    }

    suspend fun getOnAirTvShows(page: Int = 1): List<TvShow> {
        val onAirTvShow = api.getOnAirTvShows(API_KEY, page)
        return  onAirTvShow.tvShows
    }

}