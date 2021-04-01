package com.example.android.mymovieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.model.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * FROM TvShow")
    fun getAll(): List<TvShow>

    @Insert
    fun insert(tvShow: TvShow)

    @Query("SELECT * FROM TvShow where id = :id LIMIT 1")
    fun findById(id: Long) : TvShow?

    @Query("DELETE FROM TvShow WHERE id = :id")
    fun deleteTvShow (id: Long)

    @Delete
    fun deleteTvShow (tvShow: TvShow)
}