package com.msa.todo.data.repositories

import com.msa.todo.data.dao.ToDoTaskDao
import com.msa.todo.data.models.ToDoTaskEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoTaskDao: ToDoTaskDao) {

    val getAllTasks: Flow<List<ToDoTaskEntity>> = toDoTaskDao.getAllTasks()

    val sortByLowPriority: Flow<List<ToDoTaskEntity>> = toDoTaskDao.sortByLowPriority()

    val sortByHighPriority: Flow<List<ToDoTaskEntity>> = toDoTaskDao.sortByHighPriority()


    fun getSelectedTask(taskId:Int):Flow<ToDoTaskEntity>{
        return toDoTaskDao.getSelectedTask(taskId)
    }

    suspend fun deleteAllTask(){
        toDoTaskDao.deleteAllTasks()
    }

    suspend fun insertTask(toDoTaskEntity: ToDoTaskEntity){
        toDoTaskDao.insertTask(toDoTaskEntity)
    }
    fun searchDataBase(searchQuery:String):Flow<List<ToDoTaskEntity>>{
        return toDoTaskDao.searchDataBase(searchQuery)
    }

    suspend fun updateTask(toDoTaskEntity: ToDoTaskEntity){
        toDoTaskDao.updateTask(toDoTaskEntity)
    }

    suspend fun deleteTask(toDoTaskEntity: ToDoTaskEntity){
        toDoTaskDao.deleteTask(toDoTaskEntity)
    }



    suspend fun addTask(toDoTask: ToDoTaskEntity) {
        toDoTaskDao.insertTask(toDoTaskEntity = toDoTask)
    }

    suspend fun deleteAllTasks() {
        toDoTaskDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTaskEntity>> {
        return toDoTaskDao.searchDatabase(searchQuery = searchQuery)
    }
}
