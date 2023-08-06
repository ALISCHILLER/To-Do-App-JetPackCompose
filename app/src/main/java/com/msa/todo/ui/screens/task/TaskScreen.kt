package com.msa.todo.ui.screens.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.data.models.Priority
import com.msa.todo.data.models.ToDoTaskEntity
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.Action


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTaskEntity?,
    toDoViewModel: ToDoViewModel,
    navigateToListScreen: (Action) -> Unit
) {


    val title: String = toDoViewModel.title
    val description: String = toDoViewModel.description
    val priority: Priority = toDoViewModel.priority
    val context = LocalContext.current
    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

   Scaffold(
        topBar = {
            TaskTopBar(
                selectedTask = selectedTask,
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (toDoViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(context = context)
                        }
                    }
                }


            )
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                TaskContent(
                    title = title,
                    onTitleChange = {
                        toDoViewModel.updateTitle(it)
                    },
                    description = description,
                    onDescriptionChange = {
                        toDoViewModel.updateDescription(newDescription = it)
                    },
                    priority = priority,
                    onPrioritySelected = {
                        toDoViewModel.updatePriority(newPriority = it)
                    }
                )
            }
        },

        )

}


fun displayToast(context: Context) {
    Toast.makeText(
        context,
        "Fields Empty.",
        Toast.LENGTH_SHORT
    ).show()
}
