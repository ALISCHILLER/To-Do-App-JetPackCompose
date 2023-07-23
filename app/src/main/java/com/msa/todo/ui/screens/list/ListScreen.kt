package com.msa.todo.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen:(taskId:Int)-> Unit
){
    Scaffold(
        topBar = {
            ListAppBar()
        },
        content={},
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

@Composable
@Preview
private fun ListScreenPreview(){
    ListScreen(navigateToTaskScreen = {})
}