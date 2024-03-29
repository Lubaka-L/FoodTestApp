package com.example.data.networking.general

import com.google.gson.annotations.SerializedName

data class ListWrapperCategory<T>(
    @SerializedName("categories")
    val categories: T
)

data class ListWrapperDishes<T>(
    @SerializedName("meals")
    val meals: T
)