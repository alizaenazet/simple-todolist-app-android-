package com.example.todolistretrofit.network

import com.example.todolistretrofit.model.TodoList
import retrofit2.http.GET


interface TodoListApiService {
    @GET("todos")
    suspend fun getTodoList(): TodoList
}
