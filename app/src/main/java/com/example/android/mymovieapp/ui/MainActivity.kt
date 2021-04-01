package com.example.android.mymovieapp.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.android.mymovieapp.R
import com.example.android.mymovieapp.databinding.ActivityMainBinding
import com.example.android.mymovieapp.util.MOVIES_FRAGMENT
import com.example.android.mymovieapp.util.TV_SHOWS_FRAGMENT
import com.example.android.mymovieapp.util.WATCH_LIST_FRAGMENT
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchMenuItem: MenuItem
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(findViewById(R.id.movie_toolbar))
        setSupportActionBar(binding.movieToolbar)

//        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView = binding.bottomNavView
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.movies -> showMoviesFragment()
                R.id.shows -> showTvShowsFragment()
                R.id.watchlist -> showWatchListFragment()
            }
            return@setOnNavigationItemSelectedListener true
        }

        showMoviesFragment()
    }

    private fun showMoviesFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        tvShowsFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }
        if (fragment == null){
            transaction.add(R.id.fragment_container, MoviesFragment(), MOVIES_FRAGMENT)
        } else{
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun showTvShowsFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        moviesFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }
        if (fragment == null){
            transaction.add(R.id.fragment_container, TvShowFragment(), TV_SHOWS_FRAGMENT)
        } else{
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun showWatchListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        moviesFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }
        if (fragment == null){
            transaction.add(R.id.fragment_container, TvShowFragment(), WATCH_LIST_FRAGMENT)
        } else{
            transaction.show(fragment)
        }
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        searchMenuItem = menu.findItem(R.id.search_item)

        val searchView = searchMenuItem.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return true


    }
}