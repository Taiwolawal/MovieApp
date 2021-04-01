package com.example.android.mymovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.adapter.TvShowsAdapter
import com.example.android.mymovieapp.databinding.FragmentTvShowBinding
import com.example.android.mymovieapp.model.TvShow
import com.example.android.mymovieapp.viewmodel.TvShowsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TvShowFragment : Fragment() {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private  lateinit var popularTvShows: RecyclerView
    private lateinit var  popularTvShowsAdapter: TvShowsAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager
    private var popularTvShowsPage = 1

    private  lateinit var topRatedTvShows: RecyclerView
    private lateinit var  topRatedTvShowsAdapter: TvShowsAdapter
    private lateinit var topRatedTvShowsLayoutMgr: LinearLayoutManager
    private var topRatedTvShowsPage = 1

    private  lateinit var onAirTvShows: RecyclerView
    private lateinit var  onAirTvShowsAdapter: TvShowsAdapter
    private lateinit var onAirTvShowsLayoutMgr: LinearLayoutManager
    private var onAirTvShowsPage = 1

    private lateinit var viewModel: TvShowsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TvShowsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        val view =  inflater.inflate(R.layout.fragment_tv_show, container, false)
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        val view = binding.root

        popularTvShows = _binding!!.popularTvShows
        popularTvShowsLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        popularTvShows.layoutManager = popularTvShowsLayoutMgr
        popularTvShowsAdapter = TvShowsAdapter(mutableListOf()){tvShow -> showTvShowDetails(tvShow)  }
        popularTvShows.adapter = popularTvShowsAdapter

        topRatedTvShows = _binding!!.topRatedTvShows
        topRatedTvShowsLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        topRatedTvShows.layoutManager = topRatedTvShowsLayoutMgr
        topRatedTvShowsAdapter = TvShowsAdapter(mutableListOf()){tvShow -> showTvShowDetails(tvShow)  }
        topRatedTvShows.adapter = topRatedTvShowsAdapter


        onAirTvShows = _binding!!.onAirTvShows
        onAirTvShowsLayoutMgr = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        onAirTvShows.layoutManager = onAirTvShowsLayoutMgr
        onAirTvShowsAdapter = TvShowsAdapter(mutableListOf()){tvShow -> showTvShowDetails(tvShow)  }
        onAirTvShows.adapter = onAirTvShowsAdapter

        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.popularTvShows.observe(viewLifecycleOwner, { tvShows ->
            popularTvShowsAdapter.appendTvShows(tvShows)
            attachPopularTvShowsOnScrollListener()
        })

        viewModel.topRatedTvShows.observe(viewLifecycleOwner, { tvShows ->
            topRatedTvShowsAdapter.appendTvShows(tvShows)
            Log.e("Tv Top Rated", "Error")
            attachTopRatedTvShowsOnScrollListener()
        })

        viewModel.onAirTvShows.observe(viewLifecycleOwner, { tvShows ->
            onAirTvShowsAdapter.appendTvShows(tvShows)
            Log.e("On Air Tv Show", "Error")
            attachOnAirTvShowsOnScrollListener()
        })

        viewModel.error.observe(viewLifecycleOwner, {onError()})
    }

    private fun attachPopularTvShowsOnScrollListener() {
        popularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisibleItem = popularTvShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    GlobalScope.launch(Dispatchers.Main) {
                        viewModel.getPopularTvShows(popularTvShowsPage)
                    }

                }
            }
        })
    }

    private fun attachTopRatedTvShowsOnScrollListener() {
        topRatedTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTvShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedTvShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    GlobalScope.launch(Dispatchers.Main) {
                        viewModel.getTopRatedTvShows(topRatedTvShowsPage)
                    }

                }
            }
        })
    }

    private fun attachOnAirTvShowsOnScrollListener() {
        onAirTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onAirTvShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTvShowsLayoutMgr.childCount
                val firstVisibleItem = onAirTvShowsLayoutMgr.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onAirTvShows.removeOnScrollListener(this)
                    onAirTvShowsPage++
                    GlobalScope.launch(Dispatchers.Main) {
                        viewModel.getOnAirTvShows(onAirTvShowsPage)
                    }

                }
            }
        })
    }

    private fun onError() {
        Toast.makeText(activity, getString(R.string.error_fetch_movies), Toast.LENGTH_LONG)
    }

    private fun showTvShowDetails(tvShow: TvShow) {
        val intent = Intent(activity, TvShowsDetailActivity::class.java)
        intent.putExtra(TV_SHOW_ID, tvShow.id)
        intent.putExtra(TV_SHOW_BACKDROP, tvShow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvShow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvShow.name)
        intent.putExtra(TV_SHOW_RATING, tvShow.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, tvShow.firstAirDate)
        intent.putExtra(TV_SHOW_OVERVIEW, tvShow.overview)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}