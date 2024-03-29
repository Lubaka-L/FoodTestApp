package com.example.data.repository

import com.example.core.ResultWrapper
import com.example.data.networking.api.DishApi
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory
import com.example.domain.repository.DatabaseRepository
import com.example.domain.repository.Repository

class RepositoryImpl(
    private val dishApi: DishApi,
    private val databaseRepository: DatabaseRepository
) : Repository {

    override suspend fun getCategories(): ResultWrapper<List<DishCategory>> {
        return try {
            val categories = dishApi.getCategories().categories
            databaseRepository.addDishesCategory(categories)
            ResultWrapper.Success(categories)
        } catch (e: Exception) {
            return ResultWrapper.Success(
                data = databaseRepository.getDishesCategory(),
                dataSource = ResultWrapper.Success.DataSource.DATABASE
            )
        }
    }

    override suspend fun getDishes(): ResultWrapper<List<Dish>> {
        return try {
            val dishes = dishApi.getDishes().meals
            databaseRepository.addDishes(dishes)
            ResultWrapper.Success(dishes)
        } catch (e: Exception) {
            return ResultWrapper.Success(
                data = databaseRepository.getDishes(),
                dataSource = ResultWrapper.Success.DataSource.DATABASE
            )
        }
    }

}