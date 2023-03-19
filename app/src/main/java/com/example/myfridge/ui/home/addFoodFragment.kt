package com.example.myfridge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myfridge.R
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.databinding.AddFoodBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddFoodFragment : Fragment() {
    private val viewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    private var _binding: AddFoodBinding? = null
    private lateinit var editName: EditText
    private lateinit var editExpiration: DatePicker


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //viewModel.addFridgeItemInfo(FridgeItemInfo("","Apple","3/20/2023"))
        //val recipe = this.requireArguments().getSerializable("recipe") as RecipeItem

        return root
    }

    //once the view is created, we will get the edit boxes by their id's, and will create variables the get the info inside them
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.editName)
        editExpiration = view.findViewById(R.id.editDate)

        val submitButton = _binding!!.submitButton


        //This will save the value they enter. we can then create a food out of it
        val name = editName.text.toString()

        //val expirationDay = editExpiration.text.toString()
        submitButton.setOnClickListener {
            val name = editName.text.toString()

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val calendar = Calendar.getInstance()
            calendar.set(editExpiration.year, editExpiration.month + 1, editExpiration.dayOfMonth)
            val expiration = dateFormat.format(calendar.time)

            viewModel.addFridgeItemInfo(FridgeItemInfo("", name, expiration))

            findNavController().navigateUp()
        }
    }
}