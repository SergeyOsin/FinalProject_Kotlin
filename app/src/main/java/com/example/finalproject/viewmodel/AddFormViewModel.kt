package com.example.finalproject.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.finalproject.Apartament

class AddFormViewModel : ViewModel() {

    fun addApartment(rent: Int, rooms: Int, area: Double, number: Int, isRented: Boolean) {
        val newApartment = Apartament(
            Rent = rent,
            CntRooms = rooms,
            Area = area,
            ApartamentNumb = number,
            IsRented = isRented
        )

    }
}
