package com.example.myfridge.ui.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfridge.data.database.FridgeItemInfo
import com.example.myfridge.data.database.ShoppingListItemInfo
import com.example.myfridge.databinding.AddFoodBinding
import com.example.myfridge.databinding.FragmentAddShoppingListItemBinding
import com.example.myfridge.ui.database.DatabaseViewModel

class AddShoppingListItemFragment : Fragment() {
    private val viewModel: DatabaseViewModel.ShoppingListItemInfoViewModel by viewModels()

    private var _binding : FragmentAddShoppingListItemBinding? = null

    private val binding get() = _binding!!
    private lateinit var addName : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddShoppingListItemBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.title.text = "Add an Item to Shopping List"

        addName = binding.addName
        val submitButton = _binding!!.addShoppingListItemButton

        submitButton.setOnClickListener(){
            val name = addName.text.toString()
            viewModel.addShoppingListItemInfo(ShoppingListItemInfo(name))
            findNavController().navigateUp()
        }

        return root
    }
}