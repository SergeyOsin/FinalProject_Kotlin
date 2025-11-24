package com.example.finalproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Apartament

class DataBaseViewModel : ViewModel() {

    private val _apartments = MutableLiveData<List<Apartament>>()
    val apartments: LiveData<List<Apartament>> = _apartments

    init {
        loadApartments()
    }

    private fun loadApartments() {
        val apartmentList = mutableListOf(
            Apartament(1000, 3, 10.5, 3, true),
            Apartament(3500, 4, 12.0, 4, false),
            Apartament(10000, 1, 9.5, 2, false)
        )
        _apartments.value = apartmentList
    }

    fun addApartment(apartment: Apartament) {
        val currentList = _apartments.value?.toMutableList() ?: mutableListOf()
        currentList.add(apartment)
        _apartments.value = currentList
    }
}
