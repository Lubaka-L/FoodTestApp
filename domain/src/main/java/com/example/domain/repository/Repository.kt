package com.example.domain.repository

import com.example.core.ResultWrapper
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory

interface Repository {

    suspend fun getCategories(): ResultWrapper<List<DishCategory>>
    suspend fun getDishes(): ResultWrapper<List<Dish>>

}