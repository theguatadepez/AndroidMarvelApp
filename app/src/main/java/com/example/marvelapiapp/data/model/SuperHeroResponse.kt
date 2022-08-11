package com.example.marvelapiapp.data.model

import com.example.marvelapiapp.data.model.Data

data class SuperHeroResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)