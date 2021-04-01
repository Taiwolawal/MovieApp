package com.example.android.mymovieapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.mymovieapp.model.TvShow
import com.example.android.mymovieapp.networking.TvShowRepository

class TvShowsViewModel: ViewModel() {

    private val _popularTvShows = MutableLiveData<List<TvShow>>()
    val popularTvShows: LiveData<List<TvShow>>
        get() = _popularTvShows

    private val _topRatedTvShows = MutableLiveData<List<TvShow>>()
    val topRatedTvShows: LiveData<List<TvShow>>
        get() = _topRatedTvShows

    private val _onAirTvShows = MutableLiveData<List<TvShow>>()
    val onAirTvShows: LiveData<List<TvShow>>
        get() = _onAirTvShows

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private fun onError() {
        _error.value = true
    }

    private fun onPopularTvShowsFetched(tvShows: List<TvShow>) {
        _popularTvShows.value = tvShows
    }

    private fun onTopRatedTvShowsFetched(tvShows: List<TvShow>) {
        _topRatedTvShows.value = tvShows
    }

    private fun onOnAirTvShowsFetched(tvShows: List<TvShow>) {
        _onAirTvShows.value = tvShows
    }

    suspend fun getPopularTvShows(page: Int = 1) {
        TvShowRepository.getPopularTvShows(page)
    }

    suspend fun getTopRatedTvShows(page: Int = 1) {
        TvShowRepository.getTopRatedTvShows(page)
    }

    suspend fun getOnAirTvShows(page: Int = 1) {
        TvShowRepository.getOnAirTvShows(
            page
        )
    }


}