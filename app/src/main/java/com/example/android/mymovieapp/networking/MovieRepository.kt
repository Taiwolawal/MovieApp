package com.example.android.mymovieapp.networking

import android.util.Log
import com.example.android.mymovieapp.Failure
import com.example.android.mymovieapp.Result
import com.example.android.mymovieapp.Success
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.responses.MoviesResponse
import com.example.android.mymovieapp.util.API_KEY
import com.example.android.mymovieapp.util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Error

object MovieRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    suspend fun searchMovies(query: String): Result<List<Movies>> = try {
        val searchedMovies = api.searchMovie(API_KEY, query)
        Success(searchedMovies.results)
    } catch (error: Throwable){
        Failure(error)
    }

     suspend fun getTopRatedMovies(page: Int = 1): Result<List<Movies>> = try{
        val topRatedMovies = api.getTopRatedMovies(API_KEY, page)
        Success(topRatedMovies.results)
    } catch ( error: Throwable){
        Failure(error)
    }

    suspend fun getUpcomingMovies(page: Int = 1): Result<List<Movies>> = try{
        val upComingMovies = api.getUpcomingMovies(API_KEY, page)
        Success(upComingMovies.results)
    }catch (error: Throwable){
        Failure(error)
    }

    suspend fun getPopularMovies(page: Int = 1): Result<List<Movies>> = try{
        val popularMovies = api.getPopularMovies(API_KEY,page)
        Success(popularMovies.results)
    } catch (error: Throwable){
        Failure(error)
    }


}