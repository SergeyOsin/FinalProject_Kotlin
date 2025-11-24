package com.example.finalproject

import android.R.attr.onClick
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

enum class PropertyCriteria {
    RENT, NUMB, AREA, CNTROOMS, ISRENTED
}

enum class SortCriteria {
    STRAIGHT, REVERSE
}



class ApartamentList(private val apps: MutableList<Apartament>,
    private val onClick: (Apartament)->Unit): RecyclerView.Adapter
<ApartamentList.ViewHolder>(){
    class ViewHolder(view: View, private val onClick: (Apartament)->Unit):
        RecyclerView.ViewHolder(view){
        val Numb: TextView = view.findViewById(R.id.NumbApps)
        val Area: TextView=view.findViewById(R.id.Area)
        val countRooms: TextView=view.findViewById(R.id.Rooms)
        val coint: TextView=view.findViewById(R.id.Status)
        val Status: TextView=view.findViewById(R.id.Status)

        private lateinit var apartament: Apartament

        init{
            view.setOnClickListener {
                onClick(apartament)
            }
        }
        fun bind(apartament: Apartament){
            this.apartament=apartament
            Numb.text=apartament.ApartamentNumb.toString()
            Status.text=apartament.IsRented.toString()
            Area.text=apartament.Area.toString()
            coint.text=apartament.Rent.toString()
            countRooms.text=apartament.CntRooms.toString()
        }
    }

    private val innerDataBase: MutableList<Apartament> = mutableListOf()

    fun printall(): MutableList<Apartament> {
        return innerDataBase;
    }
    fun add(appart: Apartament): Boolean{
        if (innerDataBase.find {it.ApartamentNumb == appart.ApartamentNumb} == null){
            innerDataBase.add(appart)
            return true
        }
        return false
    }

    fun delete(numb: Int): Boolean{
        var apart: Apartament? = innerDataBase.find {it.ApartamentNumb == numb}
        if (apart != null){
            innerDataBase.remove(apart)
            return true
        }
        return false
    }


    infix fun filter(typeAndProperty: Pair <String, PropertyCriteria>): List<Apartament>?{
        var(crit, typeCrit) = typeAndProperty
        when(typeCrit){
            PropertyCriteria.RENT ->
                return innerDataBase.filter{
                    it.Rent.toString().startsWith(crit)
                }
            PropertyCriteria.NUMB->
                return innerDataBase.filter{
                    it.ApartamentNumb.toString().startsWith(crit)
                }
            PropertyCriteria.AREA ->
                return innerDataBase.filter {
                    it.Area.toString().startsWith(crit)
                }
            PropertyCriteria.CNTROOMS->
                return  innerDataBase.filter {
                    it.CntRooms.toString().startsWith(crit)
                }
            PropertyCriteria.ISRENTED ->
                return innerDataBase.filter {
                    it.IsRented.toString().startsWith(crit)
                }
            else -> return null
        }
    }

    //sortCrit: SortCriteria, propCrit: PropertyCriteria
    infix fun sort(sortAndPropCriterias: Pair<SortCriteria, PropertyCriteria>): Unit {
        var(sortCrit, propCrit) = sortAndPropCriterias
        when (sortCrit) {
            SortCriteria.STRAIGHT ->
                when (propCrit) {
                    PropertyCriteria.AREA -> innerDataBase.sortBy { it.Area }
                    PropertyCriteria.NUMB -> innerDataBase.sortBy { it.ApartamentNumb }
                    PropertyCriteria.RENT -> innerDataBase.sortBy { it.Rent }
                    PropertyCriteria.CNTROOMS -> innerDataBase.sortBy { it.CntRooms }
                    else-> return
                }

            SortCriteria.REVERSE ->
                when (propCrit) {
                    PropertyCriteria.AREA -> innerDataBase.sortByDescending { it.Area }
                    PropertyCriteria.NUMB -> innerDataBase.sortByDescending { it.ApartamentNumb }
                    PropertyCriteria.RENT -> innerDataBase.sortByDescending { it.Rent }
                    PropertyCriteria.CNTROOMS -> innerDataBase.sortByDescending { it.CntRooms }
                    else -> return
                }
        }
        return
    }

    fun search(numb: Int): Apartament? {
        return innerDataBase.find{it.ApartamentNumb == numb}
    }

    infix fun select(apartNumb: Int): Apartament? {
        return this.search(apartNumb)
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(
            R.layout.apps_item_layout,parent,false
        )
        return ViewHolder(view,onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ) {
        holder.bind(apps[position])
    }

    override fun getItemCount()=apps.size

}