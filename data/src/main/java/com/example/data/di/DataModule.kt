package com.example.data.di

import androidx.room.Room
import com.example.data.database.DishDatabase
import com.example.data.repository.DatabaseRepositoryImpl
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.DatabaseRepository
import com.example.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    provideAppDatabase()
    single<Repository> { RepositoryImpl(get(), get()) }
    single<DatabaseRepository> { DatabaseRepositoryImpl(get(), Dispatchers.IO) }
}

fun Module.provideAppDatabase() {
    single<DishDatabase> {
        Room.databaseBuilder(get(), DishDatabase::class.java, "dishes-database")
            .fallbackToDestructiveMigration().build()
    }
}