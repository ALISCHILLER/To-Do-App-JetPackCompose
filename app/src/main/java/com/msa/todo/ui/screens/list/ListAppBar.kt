package com.msa.todo.ui.screens.list


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.msa.todo.R
import com.msa.todo.data.models.Priority
import com.msa.todo.ui.theme.MediumPriorityColor

@Composable
fun ListAppBar() {
    DefaultListAppBar(
        onSearchClicked = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked:()->Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "text",
                color = Color.White
            )
        },
        actions={
            ListAppBarActions(onSearchClicked)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Blue
        )
    )
}


@Composable
fun ListAppBarActions(
    onSearchClicked:()->Unit
){
    SearchAction(onSearchClicked)
}

@Composable
fun SearchAction(
    onSearchClicked:()->Unit
){
    IconButton(onClick = { onSearchClicked }
    ) {
        Icon(imageVector = Icons.Filled.Search ,
            contentDescription = "Search Tasks",
            tint = MediumPriorityColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked:(Priority) -> Unit
){
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = {expanded = true}
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_filter_list)
            ,contentDescription = "Sort Tasks"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { /*TODO*/ },
                onClick = { expanded = false }
            )
        }
     }
}


@Composable
@Preview
fun defultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {}
    )
}

