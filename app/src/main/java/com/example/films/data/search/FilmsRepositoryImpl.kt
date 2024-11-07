package com.example.films.data.search

import com.example.films.data.mapper.FilmsResponseMapper
import com.example.films.data.search.dto.FilmsSearchRequest
import com.example.films.data.search.dto.FilmsSearchResponse
import com.example.films.domain.films.FilmsRepository
import com.example.films.domain.films.model.Film
import com.example.films.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmsRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: FilmsResponseMapper,
): FilmsRepository {

    override fun searchFilms(): Flow<Resource<List<Film>>> = flow {
        emit(Resource.Success(listOf<Film>(
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("риллер","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("триллер")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ываыв","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("рипвапваллер","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("ыавыва","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("риллер","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("риллер","жасы")
            ),
            Film(
                id = 1,
                localizedName = "localizedName",
                name = "name",
                year = "year",
                rating = 3.0,
                imageUrl = "year",
                description = "description",
                genres = listOf("риллер","жасы")
            ),
        )))
//        val response = networkClient.doRequest(FilmsSearchRequest())
//        when (response.resultCode) {
//            -1 -> {
//                emit(Resource.Error("Проверьте подключение к интернету"))
//            }
//            200 -> {
//                val data = mapper.map(response as FilmsSearchResponse)
//                emit(Resource.Success(data))
//            }
//            else -> {
//                emit(Resource.Error("Ошибка сервера"))
//            }
//        }
    }
}