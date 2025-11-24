package com.example.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Apartament
import com.example.finalproject.ApartamentList
import com.example.finalproject.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DataBase: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.fragment_data_base)
        val listView=findViewById<RecyclerView>(R.id.AppsList)

        val list=mutableListOf<Apartament>()
        val listAdapter= ApartamentList(list) {
            Toast.makeText(this, "Нажата квартира №${it.ApartamentNumb}", Toast.LENGTH_SHORT).show()
        }
        listAdapter.add(Apartament(1000,3,10.5,3,true))
        listAdapter.add(Apartament(3500, 4, 12.0,4,false))
        listAdapter.add(Apartament(10000,1,9.5,2,false))
        listView.adapter=listAdapter
        listView.layoutManager= LinearLayoutManager(this)

        val button: Button =findViewById(R.id.AddBut)
        button.setOnClickListener {
            listAdapter.add(Apartament(2,2,3.1,0,false))
            listAdapter.notifyItemInserted(listAdapter.itemCount-1)
        }

    }
}