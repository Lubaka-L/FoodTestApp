package com.example.data.di

import com.example.data.networking.api.DishApi
import com.example.data.networking.serialization.adapters.DishBodyAdapter
import com.example.data.networking.serialization.adapters.DishCategoryBodyAdapter
import com.example.domain.models.Dish
import com.example.domain.models.DishCategory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_CURRENCY = "https://themealdb.com/api/json/v1/1/"

val networkModule: Module = module {
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Gson> {
        GsonBuilder()
            .registerTypeAdapter(
                Dish::class.java,
                DishBodyAdapter()
            ).registerTypeAdapter(
                DishCategory::class.java,
                DishCategoryBodyAdapter()
            ).setLenient()
            .create()
    }

    single<Retrofit.Builder> {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
    }

    single<DishApi> {
        get<Retrofit.Builder>().baseUrl(BASE_URL_CURRENCY).build().create(DishApi::class.java)
    }
}