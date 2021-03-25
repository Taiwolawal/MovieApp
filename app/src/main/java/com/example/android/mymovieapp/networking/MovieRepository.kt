package com.example.android.mymovieapp.networking

import android.util.Log
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
                    val responseBody = response.body()
                responseBody?.results

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e("SearchMovies", "onFailure", t)
            }

        })
    }

    fun getTopRatedMovies(page: Int = 1, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit){
        val topRatedMovies = api.getTopRatedMovies(API_KEY, page)
        topRatedMovies.enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
               if(response.isSuccessful){
                  val responseBody = response.body()
                   if(responseBody != null){
                       onSuccess.invoke(responseBody.results)
                   } else {
                       onError.invoke()
                   }

               } else {
                   onError.invoke()
               }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }

    fun getUpcomingMovies(page: Int = 1, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit){
        val upComingMovies = api.getUpcomingMovies(API_KEY, page)
        upComingMovies.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val responseBody = response.body()
                if (responseBody != null){
                    onSuccess.invoke(responseBody.results)
                }
                else{
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }

    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit){
        val popularMovies = api.getPopularMovies(API_KEY,page)
        popularMovies.enqueue(object: Callback<MoviesResponse>{
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                val responseBody  = response.body()
                if (responseBody != null){
                    onSuccess.invoke(responseBody.results)
                } else {
                    onError.invoke()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }


}