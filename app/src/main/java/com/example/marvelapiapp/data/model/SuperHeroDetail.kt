package com.example.marvelapiapp.data.model

data class SuperHeroDetail(
    val id: Int,
    val name: String,
    val description: String,
    val comics: Int,
    val events: Int,
    val modified: String,
    val resourceURI: String,
    val series: Int,
    val stories: Int,
    val image: String,
    val urls: List<Url>
)