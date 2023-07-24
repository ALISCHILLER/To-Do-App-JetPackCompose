package com.msa.todo.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msa.todo.ui.screens.list.ListScreen
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Constants
import com.msa.todo.util.Constants.LIST_ARGUMENT_KEY
import com.msa.todo.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)-> Unit,
    toDoViewModel: ToDoViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type= NavType.StringType
        })
    ){
        ListScreen(
            navigateToTaskScreen,
            toDoViewModel
        )

    }
}