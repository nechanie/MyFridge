package com.example.myfridge.ui.fridge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myfridge.R
import com.example.myfridge.databinding.FragmentAddToShoppingBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import com.google.android.material.snackbar.Snackbar

class AddToShoppingFragment : Fragment() {
    private val viewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    private val args: AddToShoppingFragmentArgs by navArgs()

    private var _binding: FragmentAddToShoppingBinding? = null
    private lateinit var ingredientName: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddToShoppingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ingredientName = args.ingredient

        val submitButton = _binding!!.submitButton
        val name = args.ingredient

        val shoppingTV = binding.addToShoppingTv

        shoppingTV.text = getString(R.string.add_to_shopping_error)

        Snackbar.make(
            (requireActivity() as AppCompatActivity).findViewById(android.R.id.content),
            name,
            Snackbar.LENGTH_LONG
        ).show()

        submitButton.setOnClickListener {
            //val shoppingList = editShoppingList.text.toString()
            Log.d("AddToShoppingFragment", "Ingredient: ${name}")
            findNavController().navigate(R.id.nav_home)
        }
        return root
    }
}

