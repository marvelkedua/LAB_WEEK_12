package com.example.test_lab_week_12

import com.example.test_lab_week_12.api.MovieService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.model.Movie


class MovieRepository(private val movieService: MovieService) {
    // GANTI DENGAN API KEY ANDA YANG ASLI
    private val apiKey = "90895dc4461c40ff8c17c3119ba29a6f"

    // LiveData yang berisi List Movie
    private val _movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movieLiveData

    // LiveData yang berisi pesan error
    private val _errorLiveData = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _errorLiveData

    // Fetch movies dari API
    suspend fun fetchMovies() {
        try {
            // Dapatkan list popular movies dari API
            val popularMovies = movieService.getPopularMovies(apiKey)
            // Post value ke LiveData
            _movieLiveData.postValue(popularMovies.results)
        } catch (exception: Exception) {
            // Jika error, post pesan error
            _errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}