package com.shared.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailsDto(
    val id: Int,
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    @SerializedName("release_date")
    val releaseDate: String,
    val overview: String,
    val revenue: Int,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val genres: List<GenreDto>,
    @SerializedName("poster_path")
    val imagePath: String
)

data class SpokenLanguageDto(
    @SerializedName("english_name") val name: String
)

data class GenreDto(
    val name: String
)
