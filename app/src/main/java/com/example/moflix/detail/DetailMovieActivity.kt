package com.example.moflix.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moflix.data.source.local.entity.MoviesEntity
import com.example.moflix.data.source.local.entity.TvshowEntity
import com.example.moflix.databinding.ActivityDetailMovieBinding
import com.example.moflix.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]
        val moviesId = intent.getStringExtra(EXTRA_MOVIES)
        if (moviesId != null) {
            viewModel.setSelectedId(moviesId)
            if (viewModel.getTvshow().tvshowId == moviesId) {
                val tvshow = viewModel.getTvshow()
                populateTvshow(tvshow)
            } else {
                val movies = viewModel.getMovies()
                populateMovies(movies)
            }
        }

        binding.favBtn.setOnClickListener {
            Toast.makeText(this, "Favorite Button coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    private fun populateMovies(moviesEntity: MoviesEntity) {
        binding.textTitle.text = moviesEntity.title
        binding.textRelease.text = moviesEntity.releaseDate
        binding.textRating.text = moviesEntity.rating
        binding.textDescription.text = moviesEntity.description

        Glide.with(this)
            .load(moviesEntity.imagePath)
            .transform(RoundedCorners(20))
            .into(binding.imgPoster)
    }

    private fun populateTvshow(tvshowEntity: TvshowEntity) {
        binding.textTitle.text = tvshowEntity.title
        binding.textRelease.text = tvshowEntity.releaseDate
        binding.textRating.text = tvshowEntity.rating
        binding.textDescription.text = tvshowEntity.description

        Glide.with(this)
            .load(tvshowEntity.imagePath)
            .transform(RoundedCorners(20))
            .into(binding.imgPoster)
    }
}