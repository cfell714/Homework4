package com.example.homework4

import kotlinx.coroutines.flow.Flow
import org.w3c.dom.Text

class DreamRepository (private val dreamDao: DreamDAO) {

    val allDreams:Flow<List<Dream>> = dreamDao.getAlphabeticalDreams()

    suspend fun insert (dream:Dream){
        dreamDao.insert(dream)
    }

    suspend fun delete(id:Int){
        dreamDao.delete(id)
    }

    fun select(id:Int) :Flow<Dream> {
        return dreamDao.select(id)
    }


    suspend fun update(id:Int, date:String, title:String, description:String, interpretation:String, emotion:String){
        dreamDao.update(id, date, title, description, interpretation, emotion)
    }

}