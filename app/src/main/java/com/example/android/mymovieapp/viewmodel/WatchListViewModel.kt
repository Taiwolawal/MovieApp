package com.example.android.mymovieapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.android.mymovieapp.db.AppDatabase
import com.example.android.mymovieapp.model.WatchList
import com.example.android.mymovieapp.model.WatchListType

class WatchListViewModel(application: Application) :ViewModel() {

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "MovieApp"
        ).allowMainThreadQueries().build()
    }

    private  val _allMoviesAndTvShows =  MutableLiveData<List<WatchList>>()
    val allTvAndMovies: LiveData<List<WatchList>>
    get() = _allMoviesAndTvShows

    private val _movies = MutableLiveData<List<WatchList>>()
    val movies: LiveData<List<WatchList>>
    get() = _movies

    private val _tvShows = MutableLiveData<List<WatchList>>()
    val tvShows: LiveData<List<WatchList>>
        get() = _tvShows



    fun getAll(){
        val tvShows = db.tvShowDao().getAll()
        val movies = db.MovieDao().getAll()
        val watchList = mutableListOf<WatchList>()
        watchList.addAll(tvShows.map {tvShow ->
            WatchList(tvShow.id,
                tvShow.title,
                tvShow.overview,
                tvShow.posterPath,
                tvShow.backdropPath,
                tvShow.rating,
                tvShow.firstAirDate,
                WatchListType.TvShowType
            )
        })

        watchList.addAll(movies.map { movies ->
            WatchList(movies.id,
                movies.title,
            movies.overview,
            movies.posterPath,
            movies.backdropPath,
            movies.rating,
            movies.releaseDate,
                WatchListType.MovieType)
        })
        _allMoviesAndTvShows.value = watchList
    }

    fun getMovies(){
        val movies = db.MovieDao().getAll()
        val watchList =  mutableListOf<WatchList>()
        watchList.addAll(movies.map { movie ->
            WatchList(movie.id,
                movie.title,
                movie.overview,
                movie.posterPath,
                movie.backdropPath,
                movie.rating,
                movie.releaseDate,
                WatchListType.TvShowType)
        })

        _movies.value = watchList

    }

    fun getTvShows(){
        val tvShows = db.tvShowDao().getAll()
        val watchList = mutableListOf<WatchList>()
        watchList.addAll(tvShows.map {tvShow ->
            WatchList(tvShow.id,
                tvShow.title,
                tvShow.overview,
                tvShow.posterPath,
                tvShow.backdropPath,
                tvShow.rating,
                tvShow.firstAirDate,
                WatchListType.TvShowType
            )
        })
        _tvShows.value = watchList

    }

    init {
        getAll()
    }


}