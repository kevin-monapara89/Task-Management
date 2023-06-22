package com.kevin.taskmanagement.Enitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEnitiy(

    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "text") var discription: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "month") var month: String,
    @ColumnInfo(name = "year") var year: String,
    @ColumnInfo(name = "hour") var hour: String,
    @ColumnInfo(name = "minute") var minute: String,
)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}