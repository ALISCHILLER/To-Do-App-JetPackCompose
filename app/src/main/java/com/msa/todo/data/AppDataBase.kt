package com.msa.todo.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.msa.todo.data.dao.ToDoTaskDao
import com.msa.todo.data.models.ToDoTaskEntity


@Database(entities = [ToDoTaskEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase(){


    abstract fun toDoTaskDao():ToDoTaskDao
}