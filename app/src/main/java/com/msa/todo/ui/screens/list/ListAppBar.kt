@file:OptIn(ExperimentalMaterial3Api::class)

package com.msa.todo.ui.screens.list


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.todo.R
import com.msa.todo.components.PriorityItem
import com.msa.todo.data.models.Priority
import com.msa.todo.ui.theme.MediumPriorityColor
import com.msa.todo.ui.viewModel.ToDoViewModel
import com.msa.todo.util.SearchAppBarState

@Composable
fun ListAppBar(
    toDoViewModel: ToDoViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState:String
) {
    when(searchAppBarState){
        SearchAppBarState.CLOSED ->{
            DefaultListAppBar(
                onSearchClicked = {
                    toDoViewModel.searchAppBarState.value=
                        SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteClicked = {}
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { text->
                    toDoViewModel.searchTextState.value=text
                               },
                onCloseClicked = {
                    toDoViewModel.searchAppBarState.value=
                        SearchAppBarState.CLOSED
                },
                onSearchClicked ={}
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked:()->Unit,
    onSortClicked:(Priority)->Unit,
    onDeleteClicked :()->Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Task",
                color = Color.White
            )
        },
        actions={
            ListAppBarActions(
                onSearchClicked= onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked
            )
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Blue
        )
    )
}


@Composable
fun ListAppBarActions(
    onSearchClicked:()->Unit,
    onSortClicked:(Priority)->Unit,
    onDeleteClicked :()->Unit
){
    SearchAction(onSearchClicked=onSearchClicked)
    SortAction(onSortClicked=onSortClicked)
    DeleteAllAction(onDeleteClicked=onDeleteClicked)
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
        Icon(painter = painterResource(id = R.drawable.ic_filter_list),
            tint = Color.White,
            contentDescription = "Sort Tasks"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { "HIGH" },
                onClick = {
                    expanded = false 
                    onSortClicked(Priority.LOW)
                },
                leadingIcon = { PriorityItem(priority = Priority.LOW) }
            )

            DropdownMenuItem(
                text = { "HIGH" },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                leadingIcon = { PriorityItem(priority = Priority.HIGH) }
            )
            DropdownMenuItem(
                text = { "HIGH" },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
                leadingIcon = { PriorityItem(priority = Priority.NONE) }
            )

        }
     }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked :()->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            tint = Color.White,
            contentDescription = "Sort Tasks"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "DeleteAll",
                        color=Color.Blue)
                       },
                onClick = {
                    expanded = false
                    onDeleteClicked()
                }
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text:String,
    onTextChange:(String) -> Unit,
    onCloseClicked:()->Unit,
    onSearchClicked: (String) -> Unit
){

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = Color.Blue
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder={
                Text(
                    modifier = Modifier
                        .alpha(1.0f),
                    text = "Search",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.0f)
                    ,
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector =Icons.Filled.Search ,
                        contentDescription ="Search Icone",
                        tint = MaterialTheme.colorScheme.onSurface
                        )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onCloseClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.onSurface,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }

}

@Composable
@Preview
fun defultListAppBarPreview() {
    DefaultListAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}

