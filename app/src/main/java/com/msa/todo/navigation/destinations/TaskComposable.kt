package com.msa.todo.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msa.todo.ui.screens.task.TaskScreen
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Action
import com.msa.todo.util.Constants
import com.msa.todo.util.Constants.TASK_ARGUMENT_KEY

fun NavGraphBuilder.taskComposable(
    navigateToListScreen : (Action)-> Unit,
    toDoViewModel: ToDoViewModel
){
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY){
            type= NavType.IntType
        })

    ){ navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            toDoViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by toDoViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                toDoViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            toDoViewModel = toDoViewModel,
            navigateToListScreen = navigateToListScreen
        )

    }
}