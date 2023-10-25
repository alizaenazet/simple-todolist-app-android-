package com.example.todolistretrofit.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val completed: Boolean,
    val id: Int,
    val todo: String,
    val userId: Int
)