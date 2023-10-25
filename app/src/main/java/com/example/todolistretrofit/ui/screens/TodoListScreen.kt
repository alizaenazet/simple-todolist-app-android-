package com.example.todolistretrofit.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todolistretrofit.model.TodoList

@Composable
fun TodoListScreen(
    todoListViewModel: TodoListViewModel = viewModel(),
    modifier: Modifier
){
    val todoListUiState: TodoListUiState = todoListViewModel._todoListUiState

    when(todoListUiState){
        is TodoListUiState.Loading ->
            LoadingScreen(modifier = Modifier.fillMaxSize())
        is TodoListUiState.Error ->
            ErrorScreen(retryAction = { /*TODO*/ })
        is TodoListUiState.Success ->
            SuccessScreen(todolist = todoListUiState.todoList, modifier = Modifier.fillMaxWidth())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
        Image(
            modifier = modifier.size(100.dp),
            imageVector = Icons.Default.Cached,
            contentDescription = "Loading")
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(imageVector = Icons.Default.ErrorOutline, contentDescription = "Error")
        Text(text = "Something wrong", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Try again")
        }
    }
}

@Composable
fun SuccessScreen(todolist: TodoList, modifier: Modifier){
    LazyColumn(){
        items(todolist.todos) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
            ) {
                Text(text = "Judul: ${it.todo}", fontWeight = FontWeight.Bold )
                Text(text = "Status: ${it.completed}" )

            }
        }
    }
}


