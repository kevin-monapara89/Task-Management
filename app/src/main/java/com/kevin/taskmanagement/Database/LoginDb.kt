//package com.kevin.taskmanagement.Database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.kevin.taskmanagement.Dao.TaskDao
//import com.kevin.taskmanagement.Enitiy.LoginEntity
//import com.kevin.taskmanagement.Enitiy.TaskEnitiy
//
//@Database(entities = [LoginEntity::class], version = 1)
//abstract class LoginDb : RoomDatabase() {
//    companion object {
//        fun init(context: Context?): LoginDb {
//            var DB = Room.databaseBuilder(context!!, LoginDb::class.java, "user login.db")
//                .allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()
//            return DB
//        }
//    }
//    abstract fun login(): LoginDb
//}