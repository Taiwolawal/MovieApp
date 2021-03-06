package com.example.android.mymovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mymovieapp.adapter.MoviesAdapter
import com.example.android.mymovieapp.databinding.FragmentMoviesBinding
import com.example.android.mymovieapp.model.Movies
import com.example.android.mymovieapp.viewmodel.MoviesViewModel


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

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
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val view =  inflater.inflate(R.layout.fragment_movies, container, false)
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root

        popularMovies = _binding!!.popularMovies
        popularMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        popularMovies.layoutManager = popularMoviesLayoutMgr
        popularMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }
        popularMovies.adapter = popularMoviesAdapter

        topRatedMovies = _binding!!.topRatedMovies
        topRatedMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topRatedMovies.layoutManager = topRatedMoviesLayoutMgr
        topRatedMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }
        topRatedMovies.adapter = topRatedMoviesAdapter

        upComingMovies = _binding!!.upcomingMovies
        upComingMoviesLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        upComingMovies.layoutManager = upComingMoviesLayoutMgr
        upComingMoviesAdapter = MoviesAdapter(mutableListOf()){movie -> showMovieDetails(movie)  }
        upComingMovies.adapter = upComingMoviesAdapter

        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getPopularMovies()
        getTopRatedMovies()
        getUpComingMovies()

    }

    private fun getPopularMovies(){
        viewModel.getPopularMovies(popularMoviesPage)
        viewModel.popularMovies.observe(viewLifecycleOwner, { movies ->
            popularMoviesAdapter.appendMovies(movies)
            attachPopularMoviesOnScrollListener()
        })
    }

    private fun getTopRatedMovies(){
        viewModel.getTopRatedMovies(topRatedMoviesPage)
        viewModel.topRatedMovies.observe(viewLifecycleOwner, { movies ->
            topRatedMoviesAdapter.appendMovies(movies)
            attachTopRatedMoviesOnScrollListener()
        })
    }

    private fun getUpComingMovies(){
        viewModel.getUpComingMovies(upComingMoviesPage)
        viewModel.upComingMovies.observe(viewLifecycleOwner, {movies ->
            upComingMoviesAdapter.appendMovies(movies)
            attachUpcomingMoviesOnScrollListener()
        })
    }

    private fun attachPopularMoviesOnScrollListener() {
            popularMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val totalItemCount = popularMoviesLayoutMgr.itemCount
                    val visibleItemCount = popularMoviesLayoutMgr.childCount
                    val firstVisibleItem = popularMoviesLayoutMgr.findFirstVisibleItemPosition()

                    if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                        popularMovies.removeOnScrollListener(this)
                        Log.d("MoviesFragment", "Fetching top rated movies")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}