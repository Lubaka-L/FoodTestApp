package com.example.data.networking.serialization.responses

import com.google.gson.annotations.SerializedName

data class DishCategoryBody(
    @SerializedName("strCategory")
    val strCategory: String?
)