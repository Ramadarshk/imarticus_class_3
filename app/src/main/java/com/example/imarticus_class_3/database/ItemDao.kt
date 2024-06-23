package com.example.imarticus_class_3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: item)
    @Update
    suspend fun update(item: item)
    @Delete
    suspend fun delete(item: item)
    @Query("DELETE FROM item")
    suspend fun deleteAll()
    @Query("SELECT* from item Where id = :id")
    fun getitem(id:Int): Flow<item>
    @Query( "SELECT* from item order by name ASC")
    fun getItems(): Flow<List<item>>
}