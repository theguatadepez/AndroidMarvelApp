package com.example.marvelapiapp.data.model

import com.example.marvelapiapp.data.model.Item

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)