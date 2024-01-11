package com.example.uaspamperpus.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Sewa::class], version = 1, exportSchema = false)
abstract class DatabaseSewa : RoomDatabase(){
    abstract fun sewaDao(): SewaDao

    companion object{
        @Volatile
        private  var Instance: DatabaseSewa? = null

        fun getDatabase(context: Context): DatabaseSewa {
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseSewa::class.java,
                    "sewa_database")
                    .build()
                    .also{ Instance=it}
            })
        }
    }
}