package com.example.netflix


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val watchlistBtn = findViewById<Button>(R.id.watchlistBtn)
        watchlistBtn.setOnClickListener {
            startActivity(Intent(this, WatchlistActivity::class.java))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPopularMovies("f51785fcf2070eb2b374c1343a7f48c8")
            .enqueue(object : Callback<MovieResponse> {

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val movieResults = response.body()?.results ?: emptyList()

                    val movieList = movieResults.map {
                        Movie(
                            "https://image.tmdb.org/t/p/w500" + it.poster_path,
                            it.title
                        )
                    }

                    val categories = listOf(
                        Category("Popular", movieList),
                        Category("Trending", movieList),
                        Category("Top Rated", movieList)
                    )

                    recyclerView.adapter = CategoryAdapter(categories)
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}