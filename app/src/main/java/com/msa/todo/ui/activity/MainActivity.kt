package com.msa.todo.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.msa.todo.navigation.SetupNavigation
import com.msa.todo.ui.theme.ToDoAppJetPackComposeTheme
import com.msa.todo.ui.viewModel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHController: NavHostController
    private  val toDoViewModel: ToDoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ToDoAppJetPackComposeTheme {
                navHController = rememberNavController()
                SetupNavigation(
                    navHController = navHController,
                    toDoViewModel = toDoViewModel
                )
            }
        }
    }
}



