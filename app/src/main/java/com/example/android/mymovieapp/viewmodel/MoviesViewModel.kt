package com.example.android.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.networking.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel() {

    private val _popularMovies = MutableLiveData<List<Movies>>()
    val popularMovies: LiveData<List<Movies>>
        get() = _popularMovies

    private val _topRatedMovies = MutableLiveData<List<Movies>>()
    val topRatedMovies: LiveData<List<Movies>>
        get() = _topRatedMovies

    private  val _upComingMovies = MutableLiveData<List<Movies>>()
    val upComingMovies: LiveData<List<Movies>>
    get() = _upComingMovies


      fun getPopularMovies(page: Int = 1){
         viewModelScope.launch {
            val popularMovies =  MovieRepository.getPopularMovies(page)
             Log.d("Popular", "getPopularMovies: $popularMovies")
             _popularMovies.value = popularMovies
         }

    }

      fun getTopRatedMovies(page: Int = 1) {
         viewModelScope.launch {
             val topRatedMovies = MovieRepository.getTopRatedMovies(page)
             Log.d("Toprated", "getTopRatedMovies: $topRatedMovies")
             _topRatedMovies.value = topRatedMovies
         }

    }

      fun getUpComingMovies(page: Int = 1){
       viewModelScope.launch(Dispatchers.IO){
           val upComingMovies = MovieRepository.getUpComingMovies(page)
           Log.d("Upcoming", "getUpComingMovies: $upComingMovies")
           _upComingMovies.postValue(upComingMovies)
       }
    }



}