package com.msa.todo.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.todo.data.models.Priority


@Composable
fun PriorityItem(
    priority: Priority
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Canvas(modifier = Modifier.size(16.dp)){
            drawCircle(color = priority.color)
        }
        Text(
            modifier=Modifier
                .padding(start =13.dp),
            text = priority.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
fun PriorityItemPreview(){
    PriorityItem(priority = Priority.LOW)
}