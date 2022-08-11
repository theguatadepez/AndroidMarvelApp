package com.example.marvelapiapp.data.model

import com.example.marvelapiapp.data.model.ItemXXX

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)