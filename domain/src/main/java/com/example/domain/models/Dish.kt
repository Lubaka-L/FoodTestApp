package com.example.domain.models

import android.net.Uri

data class Dish(
    val category : String,
    val image: Uri,
    val title: String,
    val ingredients: List<String>,
    val price: String
)