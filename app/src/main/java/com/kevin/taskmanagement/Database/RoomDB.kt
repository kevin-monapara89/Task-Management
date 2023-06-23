package com.kevin.taskmanagement.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kevin.taskmanagement.Dao.TaskDao
import com.kevin.taskmanagement.Enitiy.TaskEnitiy


@Database(entities = [TaskEnitiy::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    companion object {
        fun init(context: Context?): RoomDB {
            var DB = Room.databaseBuilder(context!!, RoomDB::class.java, "Task.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
            return DB
        }
    }

    abstract fun task(): TaskDao
}