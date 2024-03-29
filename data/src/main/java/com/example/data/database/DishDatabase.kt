package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.DishDao
import com.example.data.database.entities.DishCategoryEntity
import com.example.data.database.entities.DishEntity

@Database(
    entities = [DishEntity::class, DishCategoryEntity::class],
    version = 1
)

abstract class DishDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao
}