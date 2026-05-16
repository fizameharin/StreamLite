package com.example.netflix

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView = findViewById<ImageView>(R.id.detailImage)
        val button = findViewById<Button>(R.id.addToWatchlistBtn)
        val titleView = findViewById<TextView>(R.id.detailTitle)

        val imageUrl = intent.getStringExtra("imageUrl")
        val title = intent.getStringExtra("title")

        titleView.text = title

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)

        button.setOnClickListener {

            val sharedPref = getSharedPreferences("watchlist", MODE_PRIVATE)
            val editor = sharedPref.edit()

            val existing = sharedPref.getStringSet("movies", mutableSetOf()) ?: mutableSetOf()
            val updated = existing.toMutableSet()

            imageUrl?.let { updated.add(it) }

            editor.putStringSet("movies", updated)
            editor.apply()

            Toast.makeText(this, "Added to Watchlist ❤️", Toast.LENGTH_SHORT).show()
        }
    }
}
