package com.example.myfridge.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.FragmentRecipesDetailedBinding

class RecipesDetailedFragment: Fragment() {
    private val args: RecipesDetailedFragmentArgs by navArgs()
    private var _binding: FragmentRecipesDetailedBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecipesDetailedBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recipe = args.recipe
        binding.detailedRecipeMainText.apply {
            text = "Detailed Recipe Page for ${recipe!!.title}"
        }
        return root
    }
}