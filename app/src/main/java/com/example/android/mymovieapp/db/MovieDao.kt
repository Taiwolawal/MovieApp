package com.example.android.mymovieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.model.TvShow

interface MovieDao {

    @Query("SELECT * FROM Movies")
    fun getAll(): List<Movies>

    @Insert
    fun insert(movies: Movies)

    @Query("SELECT * FROM Movies WHERE id = :id LIMIT 1")
    fun findById(id: Long): Movies?

    @Delete
    fun  deleteMovie(movie: Movies)

    @Query("DELETE FROM Movies WHERE id = :id")
    fun delete (id: Long)

}