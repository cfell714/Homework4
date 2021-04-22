package com.example.homework4

import kotlinx.coroutines.flow.Flow

class DreamRepository (private val dreamDao: DreamDAO) {

    val allDreams:Flow<List<Dream>> = dreamDao.getAlphabeticalDreams()

  //  val allDreams:Flow<List<Dream>> = dreamDao.getDate() // added here

    suspend fun insert (dream:Dream){
        dreamDao.insert(dream)
    }

    suspend fun delete(id:Int){
        dreamDao.delete(id)
    }

    fun select(id:Int) :Flow<Dream> {
        return dreamDao.select(id)
    }


    suspend fun update(id:Int, title:String, description:String, interpretation:String, emotion:String){
        dreamDao.update(id, title, description, interpretation, emotion)
    }

}