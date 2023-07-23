package com.msa.todo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.msa.todo.data.models.ToDoTaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoTaskDao {

    @Insert
    fun insertAll(toDoTaskEntity:List<ToDoTaskEntity>)

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllTasks() : Flow<List<ToDoTaskEntity>>


    @Query("SELECT * FROM todo_table WHERE id=:taskId")
    fun getSelectedTask(taskId:Int):Flow<ToDoTaskEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(toDoTaskEntity: ToDoTaskEntity)

    @Update
    suspend fun updateTask(toDoTask: ToDoTaskEntity)

    @Delete
    suspend fun deleteTask(toDoTask: ToDoTaskEntity)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery OR descrption LIKE :searchQuery" )
    fun searchDataBase(searchQuery:String):Flow<List<ToDoTaskEntity>>


    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority " +
            "LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority():Flow<List<ToDoTaskEntity>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority " +
            "LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority():Flow<List<ToDoTaskEntity>>

}