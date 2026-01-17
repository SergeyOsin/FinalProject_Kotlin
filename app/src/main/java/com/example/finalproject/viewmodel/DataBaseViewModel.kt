// DataBaseViewModel.kt
package com.example.finalproject.viewmodel

import androidx.lifecycle.*
import com.example.finalproject.Apartament
import com.example.finalproject.data.AppDatabase
import com.example.finalproject.data.ApartmentDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataBaseViewModelFactory(private val dao: ApartmentDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataBaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DataBaseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class DataBaseViewModel(private val dao: ApartmentDao) : ViewModel() {

    private val _apartments = MutableLiveData<List<Apartament>>()
    val apartments: LiveData<List<Apartament>> = _apartments

    init {
        viewModelScope.launch {
            dao.getAll().collect { list ->
                _apartments.postValue(list)
            }
        }
    }

    suspend fun DeleteApp(number: Int) {
        dao.deleteByNumber(number)
    }

    suspend fun findApp(Numb: Int): Boolean {
        return dao.findByNumber(Numb) != null
    }

    suspend fun findAllApps(Numb: Int): Int {
        return dao.findCountByNumber(Numb)
    }
    suspend fun UpdateApp(apps: Apartament): Boolean {
        try {
            dao.update(apps)
            return true
        } catch (e: Exception) {
            return false
        }
    }
    suspend fun addApartment(apartment: Apartament) {
        dao.insert(apartment)
    }
}
