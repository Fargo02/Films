package com.example.films.data.search.dto


class FilmsSearchResponse(val searchType: String,
                          val expression: String,
                          val results: List<FilmDto>) : Response()