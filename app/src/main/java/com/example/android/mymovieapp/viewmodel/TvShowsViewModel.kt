package com.example.android.mymovieapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.mymovieapp.model.TvShow
import com.example.android.mymovieapp.networking.TvShowRepository
import kotlinx.coroutines.launch

class TvShowsViewModel : ViewModel() {

    private val _popularTvShows = MutableLiveData<List<TvShow>>()
    val popularTvShows: LiveData<List<TvShow>>
        get() = _popularTvShows

    private val _topRatedTvShows = MutableLiveData<List<TvShow>>()
    val topRatedTvShows: LiveData<List<TvShow>>
        get() = _topRatedTvShows

    private val _onAirTvShows = MutableLiveData<List<TvShow>>()
    val onAirTvShows: LiveData<List<TvShow>>
        get() = _onAirTvShows


    fun getPopularTvShows(page: Int = 1) {
        viewModelScope.launch {
            val popularTv = TvShowRepository.getPopularTvShows(page)
            Log.i("TvShowViewModel", "getPopularTvShows: $popularTv ")
            _popularTvShows.value = popularTv
        }
    }


    fun getTopRatedTvShows(page: Int = 1){
        viewModelScope.launch {
            val topRatedTvShow = TvShowRepository.getTopRatedTvShows(page)
            _topRatedTvShows.value = topRatedTvShow
        }
    }

    fun getOnAirTvShows(page: Int = 1) {
        viewModelScope.launch {
            val onAirTv = TvShowRepository.getOnAirTvShows(page)
            _onAirTvShows.value = onAirTv
        }
    }

}