package com.example.data.networking.serialization.adapters

import com.example.data.networking.serialization.responses.DishCategoryBody
import com.example.domain.models.DishCategory
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class DishCategoryBodyAdapter : JsonDeserializer<DishCategory> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DishCategory {
        val body = context?.deserialize<DishCategoryBody>(json, DishCategoryBody::class.java)
        return DishCategory(
            title = body?.strCategory.orEmpty()
        )
    }
}