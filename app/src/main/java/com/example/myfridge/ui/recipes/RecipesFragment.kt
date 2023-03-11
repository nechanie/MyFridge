package com.example.myfridge.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.R
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.FragmentRecipesBinding
import com.example.myfridge.BuildConfig


// Keeping our API key safe
const val SPOONACULAR_APPID = BuildConfig.SPOONACULAR_API_KEY

class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var recipesRv: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recipesViewModel =
            ViewModelProvider(this).get(RecipesViewModel::class.java)

        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recipesRv = binding.rvRecipes
        recipesRv.layoutManager = LinearLayoutManager(container?.context)
        recipesAdapter = RecipesAdapter(::forwardDetailedRecipe)
        recipesRv.adapter = recipesAdapter
        recipesViewModel.recipes.observe(viewLifecycleOwner) {
            recipes -> recipesAdapter.updateRecipesList(recipes)
        }

        val searchButton : Button = root.findViewById(R.id.searchRecipesButton)
        searchButton.setOnClickListener {
            searchRecipe(recipesViewModel)
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun forwardDetailedRecipe(recipe: RecipeItem){
        val navController = this.activity?.findNavController(R.id.nav_host_fragment_content_main_activity)
        val bundle: Bundle = Bundle().apply {
            putSerializable("recipe", recipe)
        }
        navController?.navigate(R.id.action_nav_recipes_to_recipesDetailedFragment, bundle)
        return
    }

}

fun searchRecipe(viewModel: RecipesViewModel) {
    viewModel.loadRecipeResults(SPOONACULAR_APPID)
}