package com.example.films.data.search

import com.example.films.data.search.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}