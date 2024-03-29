package com.example.data.networking.api

import com.example.data.networking.general.ListWrapperCategory
import com.example.data.networking.general.ListWrapperDishes
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory
import retrofit2.Response
import retrofit2.http.GET

interface DishApi {

    @GET("categories.php")
    suspend fun getCategories(): ListWrapperCategory<List<DishCategory>>

    @GET("search.php?s")
    suspend fun getDishes(): ListWrapperDishes<List<Dish>>

}