package com.shared.domain.model

data class FilmDetails (
    val id: Int = 0,
    val title: String = "",
    val rating: Double = 0.0,
    val releaseDate: String = "",
    val overview: String = "",
    val revenue: Int = 0,
    val spokenLanguages: List<String> = emptyList(),
    val status: String = "",
    val genres: List<String> = emptyList(),
    val imagePath: String = ""
)
