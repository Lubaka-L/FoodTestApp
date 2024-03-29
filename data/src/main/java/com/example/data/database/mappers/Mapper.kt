package com.example.data.database.mappers

import android.net.Uri
import com.example.data.database.entities.DishCategoryEntity
import com.example.data.database.entities.DishEntity
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory

internal fun Dish.toDishEntity(): DishEntity {
    return DishEntity(
        image = image.toString(),
        category = category,
        title = title,
        ingredients = ingredients.joinToString(","),
        price = price
    )
}

internal fun DishCategory.toDishCategoryEntity(): DishCategoryEntity {
    return DishCategoryEntity(
        category = title
    )
}

internal fun DishEntity.toDish(): Dish {
    return Dish(
        image = Uri.parse(image),
        category = category,
        title = title,
        ingredients = ingredients.split(","),
        price = price
    )
}

internal fun DishCategoryEntity.toDishCategory(): DishCategory {
    return DishCategory(
        title = category
    )
}