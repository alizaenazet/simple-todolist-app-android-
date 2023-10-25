package com.example.todolistretrofit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodoList(
    val limit: Int,
    val skip: Int,
    @SerialName(value = "todos")
    val todos: List<Todo>,
    val total: Int
)