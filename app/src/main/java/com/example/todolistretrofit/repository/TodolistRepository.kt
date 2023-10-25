package com.example.todolistretrofit.repository

import com.example.todolistretrofit.model.Todo
import com.example.todolistretrofit.model.TodoList
import com.example.todolistretrofit.network.TodoListApiService

class TodolistRepository (
    private val todoListApiService: TodoListApiService
){
    suspend fun getTodoList():TodoList{
        return todoListApiService.getTodoList()
    }
}