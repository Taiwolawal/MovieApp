package com.example.android.mymovieapp.networking

import com.example.android.mymovieapp.Failure
import com.example.android.mymovieapp.Result
import com.example.android.mymovieapp.Success
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.model.TvShow
import com.example.android.mymovieapp.responses.TvShowResponse
import com.example.android.mymovieapp.util.API_KEY
import com.example.android.mymovieapp.util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    suspend fun getPopularTvShows(page: Int = 1): Result<List<TvShow>> = try{
        val popularTvShows = api.getPopularTvShows(API_KEY, page)
        Success(popularTvShows.tvShows)
    } catch (error: Throwable){
        Failure(error)
    }

    suspend fun getTopRatedTvShows(page: Int = 1): Result<List<TvShow>> = try{
        val topRatedTvShow = api.getTopRatedTvShows(API_KEY, page)
        Success(topRatedTvShow.tvShows)
    }catch (error: Throwable){
        Failure(error)
    }

    suspend fun getOnAirTvShows(page: Int = 1): Result<List<TvShow>> = try{
        val onAirTvShow = api.getOnAirTvShows(API_KEY, page)
        Success(onAirTvShow.tvShows)
    } catch (error: Throwable){
        Failure(error)
    }

}