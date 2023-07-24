package com.msa.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.msa.todo.navigation.destinations.listComposable
import com.msa.todo.navigation.destinations.taskComposable
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Constants.LIST_SCREEN

@Composable
fun SetupNavgtion(
    navHController: NavHostController,
    toDoViewModel: ToDoViewModel
){

    val screen= remember(navHController) {
        Screens(navHController)
    }

    NavHost(
        navController = navHController,
        startDestination = LIST_SCREEN
    ){
        listComposable(
            navigateToTaskScreen = screen.task,
            toDoViewModel
        )

        taskComposable (
            navigateToListScreen =  screen.list,
            toDoViewModel =  toDoViewModel
        )

    }

}