package com.example.myfridge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myfridge.R
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.databinding.AddFoodBinding
import com.example.myfridge.ui.database.DatabaseViewModel

class AddFoodFragment : Fragment() {
    private val viewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    private var _binding: AddFoodBinding? = null
    private lateinit var editName: EditText
    private lateinit var editExpirationDay: EditText
    private lateinit var editExpirationMonth: EditText
    private lateinit var editExpirationYear: EditText

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
        viewModel.addFridgeItemInfo(FridgeItemInfo("","Apple","3/20/2023"))
        //val recipe = this.requireArguments().getSerializable("recipe") as RecipeItem

        return root
    }

    //once the view is created, we will get the edit boxes by their id's, and will create variables the get the info inside them
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.editName)
        editExpirationDay = view.findViewById(R.id.editDay)
        editExpirationMonth = view.findViewById(R.id.editMonth)
        editExpirationYear = view.findViewById(R.id.editYear)


        //This will save the value they enter. we can then create a food out of it
        val name = editName.text.toString()
        val expirationDay = editExpirationDay.text.toString()
        val expirationMonth = editExpirationMonth.text.toString()
        val expirationYear = editExpirationYear.text.toString()
    }
}