package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.viewmodel.DataBaseViewModel

class DataBase : Fragment() {

    private val viewModel: DataBaseViewModel by activityViewModels()
    private lateinit var listAdapter: ApartamentList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_base,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listView = view.findViewById<RecyclerView>(R.id.AppsList)
        listAdapter = ApartamentList { apartment ->
            Toast.makeText(requireContext(), "Нажата квартира №${apartment.ApartamentNumb}", Toast.LENGTH_SHORT).show()
        }
        listView.adapter = listAdapter
        listView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.apartments.observe(viewLifecycleOwner) { apartments ->
            listAdapter.submitList(apartments)
        }
    }
}
