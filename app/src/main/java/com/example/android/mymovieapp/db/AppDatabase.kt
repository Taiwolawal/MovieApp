package com.example.android.mymovieapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.model.TvShow

@Database(entities = arrayOf(Movies::class, TvShow::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun MovieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}