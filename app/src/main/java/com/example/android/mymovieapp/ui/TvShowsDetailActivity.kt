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
import com.example.android.mymovieapp.databinding.ActivityTvShowsDetailBinding
import com.example.android.mymovieapp.db.AppDatabase
import com.example.android.mymovieapp.model.TvShow

const val TV_SHOW_BACKDROP = "extra_tv_show_backdrop"
const val TV_SHOW_POSTER = "extra_tv_show_poster"
const val TV_SHOW_TITLE = "extra_tv_show_title"
const val TV_SHOW_RATING = "extra_tv_show_rating"
const val TV_SHOW_RELEASE_DATE = "extra_tv_show_release_date"
const val TV_SHOW_OVERVIEW = "extra_tv_show_overview"
const val TV_SHOW_ID = "extra_tv_show_id"

class TvShowsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTvShowsDetailBinding
    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var addToWatchList: Button

    private var tvShowId = 0L
    private var tvShowBackdrop = ""
    private var tvShowPoster = ""
    private var tvShowTitle = ""
    private var tvShowRating = 0f
    private var tvShowReleaseDate = ""
    private var tvShowOverview = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvShowsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_tv_shows_detail)

        backdrop = binding.tvShowBackdrop
        poster = binding.tvShowPoster
        title = binding.tvShowTitle
        rating = binding.tvShowRating
        releaseDate = binding.tvShowReleaseDate
        overview = binding.tvShowOverview
        addToWatchList = binding.addToWatchList

        val extras = intent.extras
        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        addToWatchList.setOnClickListener {
            if (getTvShow(tvShowId) == null) {
                val entity = TvShow(
                    tvShowId,
                    tvShowTitle,
                    tvShowOverview,
                    tvShowPoster,
                    tvShowBackdrop,
                    tvShowRating,
                    tvShowReleaseDate
                )
                db.tvShowDao().insert(entity)
                addToWatchList.text = getString(R.string.remove_from_watch_list)
            } else {
                db.tvShowDao().deleteTvShow(tvShowId)
                addToWatchList.text = getString(R.string.add_to_watch_list)
            }
        }
    }

    private fun populateDetails(extras: Bundle) {
        tvShowId = extras.getLong(TV_SHOW_ID)
        tvShowBackdrop = extras.getString(TV_SHOW_BACKDROP, "")
        tvShowPoster = extras.getString(TV_SHOW_POSTER, "")
        tvShowTitle = extras.getString(TV_SHOW_TITLE, "")
        tvShowRating = extras.getFloat(TV_SHOW_RATING, 0f)
        tvShowReleaseDate = extras.getString(TV_SHOW_RELEASE_DATE, "")
        tvShowOverview = extras.getString(TV_SHOW_OVERVIEW, "")
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$tvShowBackdrop")
            .transform(CenterCrop())
            .into(backdrop)
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$tvShowPoster")
            .transform(CenterCrop())
            .into(poster)
        title.text = tvShowTitle
        rating.rating = tvShowRating / 2
        releaseDate.text = tvShowReleaseDate
        overview.text = tvShowOverview

        val tvShow = getTvShow(tvShowId)
        if (tvShow == null) {
            addToWatchList.text = getString(R.string.add_to_watch_list)
        } else {
            addToWatchList.text = getString(R.string.remove_from_watch_list)
        }
    }

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "MovieApp"
        ).allowMainThreadQueries().build()

    }

    private fun getTvShow(id: Long): TvShow? {
            return  db.tvShowDao().findById(id)
    }
}