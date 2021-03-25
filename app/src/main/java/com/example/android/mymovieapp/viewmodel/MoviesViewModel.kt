package com.example.android.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.networking.MovieRepository

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

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private  fun onError(){
        _error.value =true
    }

    private fun onPopularMoviesFetched(movies: List<Movies>) {
        _popularMovies.value = movies
    }

    private fun onTopRatedMoviesFetched(movies: List<Movies>) {
        _topRatedMovies.value = movies
    }

    private fun onUpcomingMoviesFetched(movies: List<Movies>) {
        _upComingMovies.value = movies
    }

    fun getPopularMovies(page: Int = 1){
        MovieRepository.getPopularMovies(page, ::onPopularMoviesFetched, ::onError)
    }

    fun getTopRatedMovies(page: Int = 1){
        MovieRepository.getTopRatedMovies(page, ::onTopRatedMoviesFetched, ::onError)
    }

    fun getUpComingMovies(page: Int = 1){
        MovieRepository.getUpcomingMovies(page, ::onUpcomingMoviesFetched, ::onError)
    }

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()
    }
}