package com.example.netflix

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WatchlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        val recyclerView = findViewById<RecyclerView>(R.id.watchlistRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sharedPref = getSharedPreferences("watchlist", MODE_PRIVATE)
        val movies = sharedPref.getStringSet("movies", emptySet()) ?: emptySet()

        val movieList = movies.map { Movie(it, "Saved Movie") }

        if (movieList.isEmpty()) {
            Toast.makeText(this, "No movies in watchlist yet", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = MovieAdapter(movieList)
    }
}