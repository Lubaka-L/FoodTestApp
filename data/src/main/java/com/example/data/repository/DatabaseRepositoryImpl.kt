package com.example.data.repository

import com.example.data.database.entities.DishCategoryEntity
import com.example.data.database.DishDatabase
import com.example.data.database.entities.DishEntity
import com.example.data.database.mappers.toDish
import com.example.data.database.mappers.toDishCategory
import com.example.data.database.mappers.toDishCategoryEntity
import com.example.data.database.mappers.toDishEntity
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory
import com.example.domain.repository.DatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DatabaseRepositoryImpl(
    private val dishDatabase: DishDatabase,
    private val dispatcher: CoroutineDispatcher
) : DatabaseRepository {

    override suspend fun addDishes(dishes: List<Dish>) {
        withContext(dispatcher) {
            dishDatabase.dishDao().addDish(dishes.map { it.toDishEntity() })
        }
    }

    override suspend fun getDishes(): List<Dish> {
        return withContext(dispatcher) {
            val result = mutableListOf<Dish>()
            val dish: List<DishEntity> = dishDatabase.dishDao().getDishes()
            dish.forEach { dishEntity ->
                result.add(dishEntity.toDish())
            }
            result
        }
    }

    override suspend fun addDishesCategory(dishesCategory: List<DishCategory>) {
        withContext(dispatcher) {
            dishDatabase.dishDao().addDishCategory(dishesCategory.map { it.toDishCategoryEntity() })
        }
    }

    override suspend fun getDishesCategory(): List<DishCategory> {
        return withContext(dispatcher) {
            val result = mutableListOf<DishCategory>()
            val dish: List<DishCategoryEntity> = dishDatabase.dishDao().getDishCategories()
            dish.forEach { dishCategoryEntity ->
                result.add(dishCategoryEntity.toDishCategory())
            }
            result
        }
    }
}