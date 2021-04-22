package com.example.homework4

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDAO {

    @Query("SELECT * FROM dream_table ORDER BY id ASC")
    fun getAlphabeticalDreams() : Flow<List<Dream>>
    // added extra here
    /*
    @Query("SELECT * FROM dream_table ORDER BY ")
    fun getDate() : Flow<List<Dream>>

     */
    // added above
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream:Dream)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun delete(id:Int)

    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun select(id:Int) : Flow<Dream>

    @Query("UPDATE dream_table SET title=:title, description=:description, interpretation=:interpretation, emotion=:emotion WHERE id=:id")
    suspend fun update(id:Int, title:String, description:String, interpretation:String, emotion:String)


}