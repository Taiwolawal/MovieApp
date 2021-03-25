package com.example.android.mymovieapp.networking

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

    fun getPopularTvShows(page: Int = 1, onSuccess: (tvShow: List<TvShow>) -> Unit, onError: ()-> Unit ){
        val popularTvShows = api.getPopularTvShows(API_KEY, page)
        popularTvShows.enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        onSuccess.invoke(responseBody.tvShows)
                    }else{
                        onError.invoke()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }

    fun getTopRatedTvShows(page: Int = 1, onSuccess: (tvShow: List<TvShow>) -> Unit, onError: ()-> Unit){
        val topRatedTvShow = api.getTopRatedTvShows(API_KEY, page)
        topRatedTvShow.enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        onSuccess.invoke(responseBody.tvShows)
                    }else{
                        onError.invoke()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }

    fun getOnAirTvShows(page: Int = 1, onSuccess: (tvShow: List<TvShow>) -> Unit, onError: ()-> Unit ){
        val onAirTvShow = api.getOnAirTvShows(API_KEY, page)
        onAirTvShow.enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        onSuccess.invoke(responseBody.tvShows)
                    }else{
                        onError.invoke()
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                onError.invoke()
            }

        })
    }

}