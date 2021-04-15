package com.example.android.mymovieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.model.TvShow

@Database(entities = arrayOf(Movies::class, TvShow::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract  fun MovieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}