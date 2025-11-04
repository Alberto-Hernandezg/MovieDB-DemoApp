package com.genres.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    val genres: List<GenreDto>
)

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
