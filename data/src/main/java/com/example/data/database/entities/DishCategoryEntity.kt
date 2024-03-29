package com.example.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class DishCategoryEntity(
    @PrimaryKey
    val category: String
)