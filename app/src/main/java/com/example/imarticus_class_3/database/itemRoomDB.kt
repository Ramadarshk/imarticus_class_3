package com.example.imarticus_class_3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.imarticus_class_3.MainActivity

@Database(entities = [item::class], version = 1, exportSchema = false)
abstract class itemRoomDB:RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object{
        private var INSTANCE:itemRoomDB?=null
        fun getdatabase(context: Context):itemRoomDB{
            return INSTANCE?: synchronized(this){
                /*val instance= Room.databaseBuilder(context.applicationContext,
                    itemRoomDB::class.java,"item_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE=instance
                return instance*/
                val instance = Room.databaseBuilder(context.applicationContext,
                    itemRoomDB::class.java,
                    "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}