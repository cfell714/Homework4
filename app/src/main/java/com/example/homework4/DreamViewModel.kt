package com.example.homework4

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import java.lang.IllegalArgumentException

class DreamViewModel (private val repository: DreamRepository) : ViewModel() {

    val allDreams: LiveData<List<Dream>> = repository.allDreams.asLiveData()

    fun insert(dream:Dream) = viewModelScope.launch {
        repository.insert(dream)
    }

    fun delete(id:Int) = viewModelScope.launch{
        repository.delete(id)
    }

    fun select(id:Int) : LiveData<Dream>{
        return repository.select(id).asLiveData()
    }

    fun update(id:Int, date:String, title:String, description:String, interpretation:String, emotion:String) = viewModelScope.launch{
        repository.update(id, date, title, description, interpretation, emotion)
    }


}
class DreamViewModelFactory(private val repository: DreamRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DreamViewModel::class.java)){
            return DreamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown view model class")
    }

}