package com.msa.todo.data.models


import androidx.compose.ui.graphics.Color
import com.msa.todo.ui.theme.HighPriorityColor
import com.msa.todo.ui.theme.LowPriorityColor
import com.msa.todo.ui.theme.MediumPriorityColor
import com.msa.todo.ui.theme.NonePriorityColor


enum class Priority(val color: Color){
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}