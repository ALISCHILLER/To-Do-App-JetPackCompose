@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.msa.todo.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.R
import com.msa.todo.components.DisplayAlertDialog
import com.msa.todo.data.models.Priority
import com.msa.todo.data.models.ToDoTaskEntity
import com.msa.todo.ui.theme.topAppBarBackgroundColor
import com.msa.todo.ui.theme.topAppBarContentColor
import com.msa.todo.util.Action
import java.io.StringReader


@Composable
fun TaskTopBar(
    selectedTask: ToDoTaskEntity?,
    navigateToListScreen: (Action) -> Unit
){

    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
){
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(text = "Add Task", color = topAppBarContentColor)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = topAppBarBackgroundColor ),
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked:(Action) -> Unit
){
    IconButton(onClick = {onBackClicked(Action.NO_ACTION)}) {
        Icon(
           imageVector = Icons.Filled.ArrowBack,
           contentDescription = "Back Arrow" ,
            tint = topAppBarContentColor
        )
    }
}
@Composable
fun AddAction(
    onAddClicked : (Action) -> Unit
){
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = topAppBarContentColor
        )
    }

}


@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTaskEntity,
    navigateToListScreen: (Action) -> Unit
){
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title.toString(),
                color = topAppBarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = topAppBarBackgroundColor ),

        actions = {
            ExistingTaskAppBarActions(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        }
    )
}


@Composable
fun CloseAction(
    onCloseClicked:(Action) -> Unit
){
    IconButton(onClick = {onCloseClicked(Action.NO_ACTION)}) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = topAppBarContentColor
        )
        
    }
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTaskEntity,
    navigateToListScreen: (Action) -> Unit
){
    var openDialog by remember { mutableStateOf(false) }

    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = navigateToListScreen)

    DisplayAlertDialog(
        title = stringResource(
            id = R.string.delete_task,
            selectedTask.title.toString()
        ),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title.toString()
        ),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) }
    )
}


@Composable
fun UpdateAction(
    onUpdateClicked:(Action) -> Unit
){
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector =Icons.Filled.Check ,
            contentDescription ="Update Icon",
            tint= topAppBarContentColor
            )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
){
    IconButton(onClick = { onDeleteClicked() }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete Icon",
            tint = topAppBarContentColor
            )
    }
}


@Composable
@Preview
private fun NewTaskAppBarPreview() {
    NewTaskAppBar(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
private fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTaskEntity(
            id = 0,
            title = "msa",
            descrption = "Some random text",
            priority = Priority.LOW
        ),
        navigateToListScreen = {}
    )
}