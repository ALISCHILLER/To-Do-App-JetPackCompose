package com.msa.todo.navigation

import androidx.navigation.NavHostController
import com.msa.todo.util.Action
import com.msa.todo.util.Constants
import com.msa.todo.util.Constants.LIST_SCREEN

class Screens(navHController:NavHostController){

    val list: (Int) -> Unit = { taskId ->
        navHController.navigate(route = "task/$taskId")
    }
    val task: (Action) -> Unit = { action ->
        navHController.navigate(route = "list/${action}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
}