package com.kevin.taskmanagement.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kevin.taskmanagement.Enitiy.TaskEnitiy

@Dao
interface TaskDao {


    @Insert
    fun AddTask(taskEnitiy:TaskEnitiy)

    @Query("SELECT * FROM task")
    fun GetTask() :List<TaskEnitiy>

    @Update
    fun UpdateTask(taskEnitiy: TaskEnitiy)

    @Query("Select * from task where title like  :desc")
    fun getSearchResults(desc : String) : LiveData<List<TaskEnitiy>>
    @Delete
    fun DeleteTask(taskEnitiy: TaskEnitiy)

    @Query("DELETE FROM task WHERE id= :id ")
    fun DeleteTask(id: Int)

}