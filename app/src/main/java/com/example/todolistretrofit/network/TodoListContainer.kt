package com.example.todolistretrofit.network

import com.example.todolistretrofit.repository.TodolistRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class TodoListContainer{
    private val BASE_URL = "https://dummyjson.com";

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: TodoListApiService by lazy {
        retrofit.create(TodoListApiService::class.java)
    }

    val todoListRepositories: TodolistRepository by lazy {
        TodolistRepository(retrofitService)
    }
}