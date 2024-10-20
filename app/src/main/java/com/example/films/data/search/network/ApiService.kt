package com.example.films.data.search.network

import com.example.films.data.search.dto.FilmsSearchResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/sequeniatesttask/films.json")
    suspend fun searchFilm(): FilmsSearchResponse
}