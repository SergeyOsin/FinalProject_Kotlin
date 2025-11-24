package com.example.finalproject

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView=findViewById<RecyclerView>(R.id.AppsList)

        Apartament(300,4,3.5,1,false)
        val list=mutableListOf<Apartament>()
        val listAdapter= ApartamentList(list){
            Toast.makeText(this,"Нажата квартира ${it.Rent}",Toast.LENGTH_SHORT).show()
        }
        listAdapter.add(Apartament(300,4,3.5,1,false))
        listAdapter.add(Apartament(450,2,10.5,3,true))

        listView.adapter=listAdapter
        listView.layoutManager= LinearLayoutManager(this)

        val button: Button =findViewById(R.id.AddBut)
        button.setOnClickListener {
            listAdapter.add(Apartament(0,0,0.1,0,false))
            listAdapter.notifyItemInserted(listAdapter.itemCount-1)
        }

    }
}