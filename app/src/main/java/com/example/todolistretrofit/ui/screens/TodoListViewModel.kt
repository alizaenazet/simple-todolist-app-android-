package com.example.todolistretrofit.ui.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistretrofit.model.Todo
import com.example.todolistretrofit.model.TodoList
import com.example.todolistretrofit.network.TodoListContainer
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface TodoListUiState{
    data class Success(
        val todoList: TodoList,
        val message: String = "TodoList is empty"
    ): TodoListUiState
    object Error: TodoListUiState
    object Loading: TodoListUiState
}


class TodoListViewModel(

): ViewModel(){
    private lateinit var todos: List<Todo>
    var _todoListUiState : TodoListUiState by mutableStateOf(TodoListUiState.Loading)
        private set

    init {
        getTodoList()
    }

    private fun getTodoList(){
        viewModelScope.launch {
            val listResult = TodoListContainer().todoListRepositories.getTodoList()
            _todoListUiState = TodoListUiState.Loading
            _todoListUiState = try {
                TodoListUiState.Success(todoList = listResult, message = "${listResult.total} Todos")
            }catch (e: IOException){
                TodoListUiState.Error
            }catch (e: HttpException){
                TodoListUiState.Error
            }
        }
    }
}