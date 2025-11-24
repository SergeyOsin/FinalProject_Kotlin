package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class AddForm : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*
        val listView = view.findViewById<RecyclerView>(R.id.AppsList)
        val list = mutableListOf<Apartament>()
        val listAdapter = ApartamentList(list) {
            Toast.makeText(requireContext(), "Нажата квартира №${it.ApartamentNumb}", Toast.LENGTH_SHORT).show()
        }
        listAdapter.add(Apartament(1000, 3, 10.5, 3, true))
        listAdapter.add(Apartament(3500, 4, 12.0, 4, false))
        listAdapter.add(Apartament(10000, 1, 9.5, 2, false))
        listView.adapter = listAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())

        val button: Button = view.findViewById(R.id.AddBut)
        button.setOnClickListener {
            listAdapter.add(Apartament(2, 2, 3.1, 0, false))
            listAdapter.notifyItemInserted(listAdapter.itemCount - 1)
        }
        */
    }
}