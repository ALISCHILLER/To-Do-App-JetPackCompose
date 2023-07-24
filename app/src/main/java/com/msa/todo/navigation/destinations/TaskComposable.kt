package com.msa.todo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Action
import com.msa.todo.util.Constants

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action)-> Unit,
    toDoViewModel: ToDoViewModel
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type= NavType.IntType
        })
    ){

    }
}