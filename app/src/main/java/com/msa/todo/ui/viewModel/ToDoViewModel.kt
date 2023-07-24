package com.msa.todo.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.todo.data.models.ToDoTaskEntity
import com.msa.todo.data.repositories.ToDoRepository
import com.msa.todo.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ToDoViewModel @Inject constructor(
    private  val toDoRepository:ToDoRepository
):ViewModel(){
     val searchAppBarState : MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

     val searchTextState:MutableState<String> = mutableStateOf("")

    private val _allTasks=
        MutableStateFlow<List<ToDoTaskEntity>>(emptyList())
    val allTasks:StateFlow<List<ToDoTaskEntity>> = _allTasks

    fun getAllTasks(){
        viewModelScope.launch(IO){
            toDoRepository.getAllTasks.collect{
                _allTasks.value=it
            }
        }
    }

}