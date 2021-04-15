package com.example.android.mymovieapp.model

sealed class WatchListType{
    object MovieType: WatchListType()
    object TvShowType: WatchListType()
}
