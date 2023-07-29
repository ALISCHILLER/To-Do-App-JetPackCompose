package com.msa.todo.ui.screens.list

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.R
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.SearchAppBarState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int)-> Unit,
    toDoViewModel: ToDoViewModel
){
    LaunchedEffect(key1 =true){
        Log.e(TAG, "LaunchedEffect: ")
        toDoViewModel.getAllTasks()
    }

    val allTask=toDoViewModel.allTasks.collectAsState()
    for (task in allTask.value){
        Log.d(TAG, task.title.toString())
    }
    val searchAppBarState:SearchAppBarState
    by toDoViewModel.searchAppBarState

    val searchTextState:String by toDoViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                toDoViewModel = toDoViewModel,
                searchAppBarState=searchAppBarState,
                searchTextState=searchTextState
            )
        },
        content={
            ListContent( )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen = navigateToTaskScreen)
        }
    )
}
@Composable
fun ListFab(
    navigateToTaskScreen:(taskId:Int)-> Unit
){
    FloatingActionButton(
        onClick = {
            navigateToTaskScreen(-1)
        }
    ) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.Blue
            )
    }
}

