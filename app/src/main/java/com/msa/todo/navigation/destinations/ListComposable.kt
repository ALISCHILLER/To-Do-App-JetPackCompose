package com.msa.todo.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msa.todo.ui.screens.list.ListScreen
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Action
import com.msa.todo.util.Constants
import com.msa.todo.util.Constants.LIST_ARGUMENT_KEY
import com.msa.todo.util.Constants.LIST_SCREEN
import com.msa.todo.util.toAction

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(taskId:Int)-> Unit,
    toDoViewModel: ToDoViewModel
){
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY){
            type= NavType.StringType
        })
    ){navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        var myAction by rememberSaveable { mutableStateOf(Action.NO_ACTION) }

        LaunchedEffect(key1 = myAction) {
            if(action != myAction){
                myAction = action
                toDoViewModel.updateAction(newAction = action)
            }
        }

        val databaseAction = toDoViewModel.action
        ListScreen(
            action = databaseAction,
            navigateToTaskScreen = navigateToTaskScreen,
            toDoViewModel = toDoViewModel
        )

    }
}