package com.example.finalproject
data class Apartament(var Rent: Int, var ApartamentNumb: Int, var Area: Double, var CntRooms: Int,
                      var IsRented: Boolean) {

    operator fun plus(other: Int): Apartament {
        return Apartament(this.Rent + other, this.ApartamentNumb,this.Area, this.CntRooms, this.IsRented)
    }

    operator fun minus(other: Int): Apartament {
        if (this.Rent -  other < 0) {return this}
        return Apartament(this.Rent + other,this.ApartamentNumb, this.Area, this.CntRooms, this.IsRented)
    }

}

