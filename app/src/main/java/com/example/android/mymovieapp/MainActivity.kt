package com.example.android.mymovieapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.android.mymovieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchMenuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.movie_toolbar))


        bottomNavigationView.setOnNavigationItemReselectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.movies -> moviesFragment()
                R.id.shows -> showsFragment()
                R.id.watchlist -> watchListFragment()
            }

        }




    }

    private fun watchListFragment() {

    }

    private fun showsFragment() {

    }

    private fun moviesFragment() {

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