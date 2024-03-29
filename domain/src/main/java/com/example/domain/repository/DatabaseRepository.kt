package com.example.domain.repository

import com.example.domain.models.Dish
import com.example.domain.models.DishCategory

interface DatabaseRepository {

    suspend fun addDishes(dishes: List<Dish>)
    suspend fun getDishes(): List<Dish>

    suspend fun addDishesCategory(dishesCategory: List<DishCategory>)
    suspend fun getDishesCategory(): List<DishCategory>
}