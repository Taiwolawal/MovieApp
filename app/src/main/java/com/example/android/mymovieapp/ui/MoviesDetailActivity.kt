package com.example.android.mymovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.databinding.ActivityMoviesDetailBinding
import com.example.android.mymovieapp.db.AppDatabase
import com.example.android.mymovieapp.model.Movies

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"
const val MOVIE_ID = "extra_movie_id"

class MoviesDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesDetailBinding
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var releaseDate : TextView
    private lateinit var overview: TextView
    private lateinit var addToWatchList: Button
    private lateinit var rating: RatingBar

    private var movieId = 0L
    private var movieBackdrop = ""
    private var moviePoster = ""
    private var movieTitle = ""
    private var movieRating = 0f
    private var movieReleaseDate = ""
    private var movieOverview = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_movies_detail)

        backdrop = binding.movieBackdrop
        poster = binding.moviePoster
        title = binding.movieTitle
        rating = binding.movieRating
        releaseDate = binding.movieReleaseDate
        overview = binding.movieOverview
        addToWatchList = binding.addToWatchList

        val extras = intent.extras
        if(extras != null){
            populateDetails(extras)
        }else {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        addToWatchList.setOnClickListener {
            if (getMovieId(movieId) == null){
                val entity = Movies(movieId,
                    movieTitle,
                    movieOverview,
                    moviePoster,
                    movieBackdrop,
                    movieRating,
                    movieReleaseDate)
                db.MovieDao().insert(entity)
                addToWatchList.text = getString(R.string.remove_from_watch_list)
            } else {
                db.MovieDao().delete(movieId)
                addToWatchList.text = getString(R.string.add_to_watch_list)
            }
        }
    }

    private fun populateDetails(extras: Bundle) {
        movieId = extras.getLong(MOVIE_ID)
        movieBackdrop = extras.getString(MOVIE_BACKDROP, "")
        moviePoster = extras.getString(MOVIE_POSTER, "")
        movieTitle = extras.getString(MOVIE_TITLE, "")
        movieRating = extras. getFloat(MOVIE_RATING, 0f)
        movieReleaseDate = extras.getString(MOVIE_RELEASE_DATE, "")
        movieOverview = extras.getString(MOVIE_OVERVIEW, "")

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$movieBackdrop")
            .transform(CenterCrop())
            .into(backdrop)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$moviePoster")
            .transform(CenterCrop())
            .into(poster)

        title.text = movieTitle
        releaseDate.text = movieReleaseDate
        overview.text = movieOverview
        rating.rating= movieRating/2
        val movie = getMovieId(movieId)
        if( movie == null){
            addToWatchList.text = getString(R.string.add_to_watch_list)
        } else{
            addToWatchList.text = getString(R.string.remove_from_watch_list)
        }
    }

    private  val db: AppDatabase by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "My MovieApp")
            .allowMainThreadQueries().build()
    }

    private fun getMovieId(id: Long): Movies? {
        return  db.MovieDao().findById(id)
    }
}