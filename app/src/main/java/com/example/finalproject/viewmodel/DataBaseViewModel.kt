package com.example.finalproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Apartament
import kotlinx.coroutines.*

class DataBaseViewModel : ViewModel() {

    private val _apartments = MutableLiveData<List<Apartament>>()
    val apartments: LiveData<List<Apartament>> = _apartments

    init {
        loadApartments()
    }

    private fun loadApartments() {
        val apartmentList = mutableListOf(
            Apartament(3, 4.1 ,100, 3, true),
            Apartament(4, 4.2, 1251, 4, true),
            Apartament(5, 13.1, 9331, 2, false)
        )
        _apartments.value = apartmentList
    }

    suspend fun DeleteApp(number: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val list = apartments.value?.toMutableList() ?: return@launch
            list.removeIf { it.ApartamentNumb == number }
            _apartments.postValue(list)  // postValue для безопасного обновления с другого потока
        }.join()  // Ждём завершения, чтобы метод был suspend
    }

    suspend fun findApp(Numb: Int): Boolean {
        return withContext(Dispatchers.Default) {
            val list = apartments.value ?: emptyList()
            list.any { it.ApartamentNumb == Numb }
        }
    }

    suspend fun findAllApps(Numb: Int): Int {
        return withContext(Dispatchers.Default) {
            val list = apartments.value ?: emptyList()
            list.count { it.ApartamentNumb == Numb }
        }
    }

    suspend fun UpdateApp(apps: Apartament): Boolean {
        return withContext(Dispatchers.Default) {
            val currentList = apartments.value?.toMutableList() ?: return@withContext false
            val index = currentList.indexOfFirst { it.ApartamentNumb == apps.ApartamentNumb }
            if (index != -1) {
                currentList[index] = apps
                _apartments.postValue(currentList)
                true
            } else false
        }
    }

    suspend fun addApartment(apartment: Apartament) {
        viewModelScope.launch(Dispatchers.Default) {
            val currentList = apartments.value?.toMutableList() ?: mutableListOf()
            currentList.add(apartment)
            _apartments.postValue(currentList)
        }.join()
    }
}
