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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
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
import com.msa.todo.ui.theme.fabBackgroundColor
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Action
import com.msa.todo.util.SearchAppBarState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    action: Action,
    navigateToTaskScreen:(taskId:Int)-> Unit,
    toDoViewModel: ToDoViewModel
){
    LaunchedEffect(key1 =true){
        toDoViewModel.handleDatabaseActions(action = action)
    }

    val searchAppBarState: SearchAppBarState = toDoViewModel.searchAppBarState
    val searchTextState: String = toDoViewModel.searchTextState
    val allTasks by toDoViewModel.allTasks.collectAsState()
    val searchedTasks by toDoViewModel.searchedTasks.collectAsState()
    val sortState by toDoViewModel.sortState.collectAsState()
    val lowPriorityTasks by toDoViewModel.lowPriorityTasks.collectAsState()
    val highPriorityTasks by toDoViewModel.highPriorityTasks.collectAsState()






    val snackbarHostState = remember { SnackbarHostState() }
    DisplaySnackBar(
        scaffoldState = snackbarHostState,
        onComplete = { toDoViewModel.updateAction(newAction = it) },
        onUndoClicked = { toDoViewModel.updateAction(newAction = it) },
        taskTitle = toDoViewModel.title,
        action = action
    )

    Scaffold(
        snackbarHost  ={ SnackbarHost(snackbarHostState) },
        topBar = {
            ListAppBar(
                toDoViewModel = toDoViewModel,
                searchAppBarState=searchAppBarState,
                searchTextState=searchTextState
            )
        },
        content={
            ListContent(
                allTasks = allTasks,
                searchedTasks = searchedTasks,
                lowPriorityTasks = lowPriorityTasks,
                highPriorityTasks = highPriorityTasks,
                sortState = sortState,
                searchAppBarState = searchAppBarState,
                onSwipeToDelete = { action, task ->
                    toDoViewModel.updateAction(newAction = action)
                    toDoViewModel.updateTaskFields(selectedTask = task)
                    snackbarHostState.currentSnackbarData?.dismiss()
                },
                navigateToTaskScreen = navigateToTaskScreen

            )
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
        },
        contentColor = fabBackgroundColor
    ) {
        Icon(imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.Blue
            )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: SnackbarHostState,
    onComplete: (Action) -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            val snackBarResult = scaffoldState.showSnackbar(
                message = setMessage(action = action, taskTitle = taskTitle),
                actionLabel = setActionLabel(action = action)
            )
            undoDeletedTask(
                action = action,
                snackBarResult = snackBarResult,
                onUndoClicked = onUndoClicked
            )
            onComplete(Action.NO_ACTION)
        }
    }
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All Tasks Removed."
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed
        && action == Action.DELETE
    ) {
        onUndoClicked(Action.UNDO)
    }
}