package com.shared.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponse(
    @SerializedName("results")
    val films: List<FilmDto>
)

@Serializable
data class FilmDto(
    val id: Int,
    val title: String,
    @SerializedName("vote_average")
    val rating: Double
)
