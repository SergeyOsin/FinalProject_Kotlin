package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels // Changed from viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.viewmodel.DataBaseViewModel // Changed ViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText

class AddForm : Fragment() {

    // Use the shared DataBaseViewModel
    private val viewModel: DataBaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberEditText = view.findViewById<TextInputEditText>(R.id.number_edit_text)
        val areaEditText = view.findViewById<TextInputEditText>(R.id.area_edit_text)
        val roomsEditText = view.findViewById<TextInputEditText>(R.id.rooms_edit_text)
        val rentEditText = view.findViewById<TextInputEditText>(R.id.rent_edit_text)
        val rentedSwitch = view.findViewById<SwitchMaterial>(R.id.rented_switch)
        val saveButton = view.findViewById<Button>(R.id.addBut)

        saveButton.setOnClickListener {
            val numberStr = numberEditText.text.toString()
            val areaStr = areaEditText.text.toString()
            val roomsStr = roomsEditText.text.toString()
            val rentStr = rentEditText.text.toString()

            if (numberStr.isBlank() || areaStr.isBlank() || roomsStr.isBlank() || rentStr.isBlank()) {
                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val newApartment = Apartament(
                    ApartamentNumb = numberStr.toInt(),
                    Area = areaStr.toDouble(),
                    CntRooms = roomsStr.toInt(),
                    Rent = rentStr.toInt(),
                    IsRented = rentedSwitch.isChecked
                )

                viewModel.addApartment(newApartment)

                Toast.makeText(requireContext(), "Квартира добавлена!", Toast.LENGTH_SHORT).show()

                findNavController().navigateUp()

            } catch (e: NumberFormatException) {
                Toast.makeText(requireContext(), "Пожалуйста, введите корректные числовые значения", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
