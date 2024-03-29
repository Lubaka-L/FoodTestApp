package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entities.DishCategoryEntity
import com.example.data.database.entities.DishEntity

@Dao
interface DishDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDishCategory(dishCategory: List<DishCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDish(dish: List<DishEntity>)

    @Query("SELECT * FROM categories_table ORDER BY category ASC")
    suspend fun getDishCategories(): List<DishCategoryEntity>

    @Query("SELECT * FROM dish WHERE category IS category")
    suspend fun getDishes(): List<DishEntity>
}