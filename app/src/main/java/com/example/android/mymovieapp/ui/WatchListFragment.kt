package com.example.android.mymovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.adapter.WatchListAdapter
import com.example.android.mymovieapp.databinding.FragmentWatchListBinding
import com.example.android.mymovieapp.model.WatchList
import com.example.android.mymovieapp.model.WatchListType
import com.example.android.mymovieapp.viewmodel.WatchListViewModel


class WatchListFragment : Fragment() {

    private var _binding: FragmentWatchListBinding? = null
    private val binding get() = _binding!!

    private lateinit var watchListRecyclerView: RecyclerView
    private lateinit var watchListSpinner: Spinner
    private lateinit var watchListAdapter: WatchListAdapter
    private lateinit var watchListViewModel: WatchListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        watchListViewModel = ViewModelProvider(this).get(WatchListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWatchListBinding.inflate(inflater, container, false)
        watchListSpinner = _binding!!.watchlistSpinner
        watchListRecyclerView = _binding!!.watchlistRecyclerview
        watchListRecyclerView.layoutManager = GridLayoutManager(context,3)
        watchListAdapter = WatchListAdapter(listOf()){
            when(it.type){
                is WatchListType.MovieType -> showMovieDetails(it)
                is WatchListType.TvShowType -> showTvDetails(it)
            }
        }
        watchListRecyclerView.adapter = watchListAdapter


        ArrayAdapter.createFromResource(context!!, R.array.watch_list_filter, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                watchListSpinner.adapter = adapter
            }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesAndTvShows()
        showMovies()
        showTvShows()
    }

    override fun onResume() {
        super.onResume()
        when(watchListSpinner.selectedItemPosition){
            0 -> watchListViewModel.getAll()
            1 -> watchListViewModel.getMovies()
            2 -> watchListViewModel.getTvShows()
        }
    }

    override fun onStart() {
        super.onStart()
        watchListSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> watchListViewModel.getAll()
                    1 -> watchListViewModel.getMovies()
                    2 -> watchListViewModel.getTvShows()
                }
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            when (watchListSpinner.selectedItemPosition) {
                0 -> watchListViewModel.getAll()
                1 -> watchListViewModel.getMovies()
                2 -> watchListViewModel.getTvShows()
            }
        }
    }

    private fun moviesAndTvShows(){
        watchListViewModel.allTvAndMovies.observe(viewLifecycleOwner, { all ->
            watchListAdapter.updateItems(all)
        })
    }

    private fun showMovies(){
        watchListViewModel.movies.observe(viewLifecycleOwner, { movies ->
            watchListAdapter.updateItems(movies)
        })
    }

    private fun showTvShows(){
        watchListViewModel.tvShows.observe(viewLifecycleOwner, { tvShows ->
            watchListAdapter.updateItems(tvShows)
        })
    }

    private fun showTvDetails(item: WatchList) {
        val intent = Intent(activity, TvShowsDetailActivity::class.java)
        intent.putExtra(TV_SHOW_ID, item.id)
        intent.putExtra(TV_SHOW_BACKDROP, item.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, item.posterPath)
        intent.putExtra(TV_SHOW_TITLE, item.title)
        intent.putExtra(TV_SHOW_RATING, item.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, item.releaseDate)
        intent.putExtra(TV_SHOW_OVERVIEW, item.overview)
        startActivity(intent)
    }

    private fun showMovieDetails(item: WatchList) {
        val intent = Intent(activity, MoviesDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, item.id)
        intent.putExtra(MOVIE_BACKDROP, item.backdropPath)
        intent.putExtra(MOVIE_POSTER, item.posterPath)
        intent.putExtra(MOVIE_TITLE, item.title)
        intent.putExtra(MOVIE_RATING, item.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, item.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, item.overview)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}