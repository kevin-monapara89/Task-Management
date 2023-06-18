package com.kevin.taskmanagement.Enitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEnitiy(

    @ColumnInfo(name = "Task") var Task: String
) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}