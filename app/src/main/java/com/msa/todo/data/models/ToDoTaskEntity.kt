package com.msa.todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.msa.todo.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String?,
    val descrption:String?,
    val priority:Priority
){
}