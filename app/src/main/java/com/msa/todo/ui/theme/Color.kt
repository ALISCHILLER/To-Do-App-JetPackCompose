package com.msa.todo.ui.theme

import android.provider.CalendarContract
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val LowPriorityColor= Color(0xFF00C980)
val MediumPriorityColor= Color(0xFFFFC114)
val HighPriorityColor= Color(0xFFFF4646)
val NonePriorityColor= Color(0xFFFFFFFF)


val Teal200 = Color(0xFF03DAC5)
val Purple700 = Color(0xFF3700B3)
val Purple500 = Color(0xFF6200EE)
val MediumGray = Color(0xFF9C9C9C)

val taskItemTextColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) DarkGray else LightGray

val taskItemBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else DarkGray

val fabBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Teal200 else Purple700

val topAppBarContentColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.White else LightGray

val topAppBarBackgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Purple500 else Color.Black


