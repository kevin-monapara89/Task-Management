package com.kevin.taskmanagement.Enitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")

data class LoginEntity(

    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "Lpassword") var lpassword: String,
)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
