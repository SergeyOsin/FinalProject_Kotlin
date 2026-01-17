package com.example.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.example.finalproject.viewmodel.DataBaseViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.example.finalproject.viewmodel.AddFormViewModel
import kotlinx.coroutines.launch

class AddForm : Fragment() {

    private lateinit var viewModel: DataBaseViewModel

    private val addform: AddFormViewModel by activityViewModels()

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
        val addButton = view.findViewById<Button>(R.id.addBut)
        val delButton = view.findViewById<Button>(R.id.delBut)
        val changeButton = view.findViewById<Button>(R.id.ChangeBut)
        val FindButton = view.findViewById<Button>(R.id.FindBut)

        addButton.setOnClickListener {
            val numberStr = numberEditText.text.toString()
            val areaStr = areaEditText.text.toString()
            val roomsStr = roomsEditText.text.toString()
            val rentStr = rentEditText.text.toString()

            if (numberStr.isEmpty() || areaStr.isEmpty() ||
                roomsStr.isEmpty() || rentStr.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val newApartment = Apartament(
                    numberStr.toInt(),
                    areaStr.toDouble(),
                    rentStr.toInt(),
                    roomsStr.toInt(),
                    rentedSwitch.isChecked
                )
                if (!viewModel.findApp(numberStr.toInt())) {
                    viewModel.addApartment(newApartment)
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} добавлена!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} уже есть в базе!", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigateUp()
            }
        }

        delButton.setOnClickListener {
            val numberStr = numberEditText.text.toString()
            val areaStr = areaEditText.text.toString()
            val roomsStr = roomsEditText.text.toString()
            val rentStr = rentEditText.text.toString()

            if (numberStr.isEmpty() || areaStr.isEmpty() ||
                roomsStr.isEmpty() || rentStr.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val app = Apartament(numberStr.toInt(),
                    areaStr.toDouble(),
                    rentStr.toInt(),
                    roomsStr.toInt(),
                    rentedSwitch.isChecked)
                if (!viewModel.findApp(app.ApartamentNumb)) {
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} не найдена в базе!", Toast.LENGTH_SHORT).show()
                } else {
                    // Если viewModel.DeleteApp не suspend, оберните его в viewModelScope.launch(Dispatchers.IO) внутри ViewModel
                    viewModel.DeleteApp(numberStr.toInt())
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} удалена!", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigateUp()
            }
        }

        changeButton.setOnClickListener {
            val numberStr = numberEditText.text.toString()
            val areaStr = areaEditText.text.toString()
            val roomsStr = roomsEditText.text.toString()
            val rentStr = rentEditText.text.toString()

            if (numberStr.isEmpty() || areaStr.isEmpty() ||
                roomsStr.isEmpty() || rentStr.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val app = Apartament(numberStr.toInt(),
                    areaStr.toDouble(),
                    rentStr.toInt(),
                    roomsStr.toInt(),
                    rentedSwitch.isChecked)
                if (!viewModel.findApp(numberStr.toInt())) {
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} не найдена в базе!", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.UpdateApp(app)
                    Toast.makeText(requireContext(), "Данные квартиры с номером ${numberStr} обновлены!", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigateUp()
            }
        }

        FindButton.setOnClickListener {
            val numberStr = numberEditText.text.toString()
            val areaStr = areaEditText.text.toString()
            val roomsStr = roomsEditText.text.toString()
            val rentStr = rentEditText.text.toString()

            if (numberStr.isEmpty() || areaStr.isEmpty() ||
                roomsStr.isEmpty() || rentStr.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля!",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                if (viewModel.findApp(numberStr.toInt())) {
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} найдена!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Квартира с номером ${numberStr} не найдена!", Toast.LENGTH_SHORT).show()
                }
                findNavController().navigateUp()
            }
        }
    }
}
