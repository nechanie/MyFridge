package com.example.myfridge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myfridge.R
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.AddFoodBinding

class AddFoodFragment : Fragment() {
    private var _binding: AddFoodBinding? = null
    private lateinit var editName: EditText
    private lateinit var editExpiration: EditText

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = AddFoodBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val recipe = this.requireArguments().getSerializable("recipe") as RecipeItem

        return root
    }

    //once the view is created, we will get the edit boxes by their id's, and will create variables the get the info inside them
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editName = view.findViewById(R.id.editName)
        editExpiration = view.findViewById(R.id.editExpiration)


        //This will save the value they enter. we can then create a food out of it
        val name = editName.text.toString()
        val expiration = editExpiration.text.toString()
    }
}