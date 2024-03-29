package com.example.data.database.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dish",
)
data class DishEntity(
    @PrimaryKey
    val image: String,
    val category : String,
    val title: String,
    val ingredients: String,
    val price: String
)