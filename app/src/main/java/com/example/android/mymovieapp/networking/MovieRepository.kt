package com.example.android.mymovieapp.networking

import android.util.Log
import com.example.android.mymovieapp.responses.MoviesResponse
import com.example.android.mymovieapp.util.API_KEY
import com.example.android.mymovieapp.util.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

    fun searchMovies(query: String) {
        val searchedMovies = api.searchMovie(API_KEY, query)
        searchedMovies.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("SearchMovies", "onFailure", t)
            }

        })
    }

    fun getTopRatedMovies(page: Int = 1){
        val topRatedMovies = api.getTopRatedMovies(API_KEY, page)
        topRatedMovies.enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getUpcomingMovies(page: Int = 1){
        val upComingMovies = api.getUpcomingMovies(API_KEY, page)
        upComingMovies.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getPopularMovies(page: Int = 1){
        val popularMovies = api.getPopularMovies(API_KEY,page)
        popularMovies.enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}