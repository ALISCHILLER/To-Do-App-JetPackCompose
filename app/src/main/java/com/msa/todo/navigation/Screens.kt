package com.msa.todo.navigation

import androidx.navigation.NavHostController
import com.msa.todo.util.Action
import com.msa.todo.util.Constants
import com.msa.todo.util.Constants.LIST_SCREEN

class Screens(navHController:NavHostController){

    val list:(Action)-> Unit ={action ->
        navHController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){
                inclusive=true
            }
        }
    }

    val task:(Int) -> Unit ={taskId->
        navHController.navigate("task/$taskId")
    }
}