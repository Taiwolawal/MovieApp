package com.example.android.mymovieapp.networking

import com.example.android.mymovieapp.Failure
import com.example.android.mymovieapp.Result
import com.example.android.mymovieapp.Success
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.util.API_KEY
import com.example.android.mymovieapp.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository{

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

    suspend fun getTopRatedMovies(page: Int = 1): List<Movies> {
        val topRatedMovies = api.getTopRatedMovies(API_KEY,page)
        return topRatedMovies.results
    }

    suspend fun getUpComingMovies(page: Int = 1): List<Movies>{
        val upComingMovies = api.getUpcomingMovies(API_KEY, page)
        return  upComingMovies.results
    }

    suspend fun getPopularMovies(page: Int = 1): List<Movies>{
        val popularMovies = api.getPopularMovies(API_KEY, page)
        return  popularMovies.results
    }



}