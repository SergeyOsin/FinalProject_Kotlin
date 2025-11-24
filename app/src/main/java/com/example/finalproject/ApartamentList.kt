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
        val coint: TextView=view.findViewById(R.id.Rent)
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
            if (apartament.IsRented) Status.text="Сдана"
            else Status.text="Несдана"
            Area.text=apartament.Area.toString() + " м2"
            coint.text=apartament.Rent.toString() + " руб"
            countRooms.text=apartament.CntRooms.toString()
        }
    }
    
    fun printall(): MutableList<Apartament> {
        return apps;
    }
    fun add(appart: Apartament): Boolean{
        if (apps.find {it.ApartamentNumb == appart.ApartamentNumb} == null){
            apps.add(appart)
            return true
        }
        return false
    }

    fun delete(numb: Int): Boolean{
        var apart: Apartament? = apps.find {it.ApartamentNumb == numb}
        if (apart != null){
            apps.remove(apart)
            return true
        }
        return false
    }


    infix fun filter(typeAndProperty: Pair <String, PropertyCriteria>): List<Apartament>?{
        var(crit, typeCrit) = typeAndProperty
        when(typeCrit){
            PropertyCriteria.RENT ->
                return apps.filter{
                    it.Rent.toString().startsWith(crit)
                }
            PropertyCriteria.NUMB->
                return apps.filter{
                    it.ApartamentNumb.toString().startsWith(crit)
                }
            PropertyCriteria.AREA ->
                return apps.filter {
                    it.Area.toString().startsWith(crit)
                }
            PropertyCriteria.CNTROOMS->
                return  apps.filter {
                    it.CntRooms.toString().startsWith(crit)
                }
            PropertyCriteria.ISRENTED ->
                return apps.filter {
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
                    PropertyCriteria.AREA -> apps.sortBy { it.Area }
                    PropertyCriteria.NUMB -> apps.sortBy { it.ApartamentNumb }
                    PropertyCriteria.RENT -> apps.sortBy { it.Rent }
                    PropertyCriteria.CNTROOMS -> apps.sortBy { it.CntRooms }
                    else-> return
                }

            SortCriteria.REVERSE ->
                when (propCrit) {
                    PropertyCriteria.AREA -> apps.sortByDescending { it.Area }
                    PropertyCriteria.NUMB -> apps.sortByDescending { it.ApartamentNumb }
                    PropertyCriteria.RENT -> apps.sortByDescending { it.Rent }
                    PropertyCriteria.CNTROOMS -> apps.sortByDescending { it.CntRooms }
                    else -> return
                }
        }
        return
    }

    fun search(numb: Int): Apartament? {
        return apps.find{it.ApartamentNumb == numb}
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