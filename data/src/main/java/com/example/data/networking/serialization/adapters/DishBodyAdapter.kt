package com.example.data.networking.serialization.adapters

import android.net.Uri
import com.example.data.networking.serialization.responses.DishBody
import com.example.domain.models.Dish
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class DishBodyAdapter : JsonDeserializer<Dish> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Dish {
        val body = context?.deserialize<DishBody>(json, DishBody::class.java)
        val listIngredients = mutableListOf<String>()
        return Dish(
            title = body?.strMeal.orEmpty(),
            image = body?.strMealThumb?.let { Uri.parse(body.strMealThumb) } ?: Uri.EMPTY,
            price = "400p.",
            category = body?.strCategory.orEmpty(),
            ingredients =
            listIngredients.apply {
                add(body?.strIngredient1.orEmpty())
                add(body?.strIngredient2.orEmpty())
                add(body?.strIngredient3.orEmpty())
                add(body?.strIngredient4.orEmpty())
                add(body?.strIngredient5.orEmpty())
                add(body?.strIngredient6.orEmpty())
                add(body?.strIngredient7.orEmpty())
                add(body?.strIngredient8.orEmpty())
                add(body?.strIngredient9.orEmpty())
                add(body?.strIngredient10.orEmpty())
                add(body?.strIngredient11.orEmpty())
                add(body?.strIngredient12.orEmpty())
                add(body?.strIngredient13.orEmpty())
                add(body?.strIngredient14.orEmpty())
                add(body?.strIngredient15.orEmpty())
                add(body?.strIngredient16.orEmpty())
                add(body?.strIngredient17.orEmpty())
                add(body?.strIngredient18.orEmpty())
                add(body?.strIngredient19.orEmpty())
                add(body?.strIngredient20.orEmpty())
            }
        )
    }
}