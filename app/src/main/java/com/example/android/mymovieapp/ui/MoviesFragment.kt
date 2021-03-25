package com.example.android.mymovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.adapter.MoviesAdapter
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.viewmodel.MoviesViewModel


class MoviesFragment : Fragment() {

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter
    private lateinit var popularMoviesLayoutMgr: LinearLayoutManager
    private var popularMoviesPage = 1

    private lateinit var topRatedMovies: RecyclerView
    private lateinit var topRatedMoviesAdapter: MoviesAdapter
    private lateinit var topRatedMoviesLayoutMgr: LinearLayoutManager
    private var topRatedMoviesPage = 1

    private lateinit var upComingMovies: RecyclerView
    private lateinit var upComingMoviesAdapter: MoviesAdapter
    private lateinit var upComingMoviesLayoutMgr: LinearLayoutManager
    private var upComingMoviesPage = 1

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_movies, container, false)

        popularMovies = view.findViewById(R.id.popular_movies)
        popularMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }

        topRatedMovies = view.findViewById(R.id.top_rated_movies)
        topRatedMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }

        upComingMovies = view.findViewById(R.id.upcoming_movies)
        upComingMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        upComingMovies.layoutManager = topRatedMoviesLayoutMgr
        upComingMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }

        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.popularMovies.observe(this, Observer { movies ->
            popularMoviesAdapter.appendMovies(movies)
            attachPopularMoviesOnScrollListener()
        })

        viewModel.topRatedMovies.observe(this, Observer { movies ->
            topRatedMoviesAdapter.appendMovies(movies)
            attachTopRatedMoviesOnScrollListener()
        })

        viewModel.upComingMovies.observe(this, Observer { movies ->
            upComingMoviesAdapter.appendMovies(movies)
            attachUpcomingMoviesOnScrollListener()
        })
        viewModel.error.observe(this, Observer { onError() })
    }

    private fun attachPopularMoviesOnScrollListener() {
        popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularMoviesLayoutMgr.itemCount
                val visibleItemCount = popularMoviesLayoutMgr.childCount
                val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularMovies.removeOnScrollListener(this)
                    popularMoviesPage++
                    viewModel.getPopularMovies(popularMoviesPage)
                }
            }
        })
    }

    private fun attachTopRatedMoviesOnScrollListener() {
        topRatedMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedMoviesLayoutMgr.itemCount
                val visibleItemCount = topRatedMoviesLayoutMgr.childCount
                val firstVisibleItem = topRatedMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedMovies.removeOnScrollListener(this)
                    topRatedMoviesPage++
                    viewModel.getTopRatedMovies(topRatedMoviesPage)
                }
            }
        })
    }

    private fun attachUpcomingMoviesOnScrollListener() {
        upComingMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upComingMoviesLayoutMgr.itemCount
                val visibleItemCount = upComingMoviesLayoutMgr.childCount
                val firstVisibleItem = upComingMoviesLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upComingMovies.removeOnScrollListener(this)
                    upComingMoviesPage++
                    viewModel.getUpComingMovies(upComingMoviesPage)
                }
            }
        })
    }

    private fun showMovieDetails(movie: Movies) {
        val intent = Intent(activity, MoviesDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, movie.id)
        intent.putExtra(MOVIE_BACKDROP, movie.backdropPath)
        intent.putExtra(MOVIE_POSTER, movie.posterPath)
        intent.putExtra(MOVIE_TITLE, movie.title)
        intent.putExtra(MOVIE_RATING, movie.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, movie.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, movie.overview)
        startActivity(intent)
    }

    private fun onError() {
        Toast.makeText(activity, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}