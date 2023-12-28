package com.mphstar.jetnime.model

data class ModelAnime(
    val id: Int,
    val title: String,
    val image: Int,
    val description: String,
    val score: Double,
    val genre: List<String>
)
