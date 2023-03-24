package com.example.myfridge.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.BuildConfig
import com.example.myfridge.R
import com.example.myfridge.data.RecyclerStatus
import com.example.myfridge.data.database.APICallInfo
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.FragmentRecipesBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import com.example.myfridge.ui.home.HomeAdapter
import com.kennyc.view.MultiStateView

const val SPOONACULAR_APPID = BuildConfig.SPOONACULAR_API_KEY
class RecipesFragment : Fragment() {
    private val databaseViewModel: DatabaseViewModel.APICallInfoViewModel by viewModels()
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
            ViewModelProvider(this)[RecipesViewModel::class.java]

        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recipesRv = binding.rvRecipes
        recipesRv.layoutManager = LinearLayoutManager(container?.context)
        recipesAdapter = RecipesAdapter(::forwardDetailedRecipe)
        recipesRv.adapter = recipesAdapter

        recipesViewModel.status.observe(viewLifecycleOwner){status ->
            when(status){
                RecyclerStatus.LOADING -> {
                    binding.multiStateView.viewState = MultiStateView.ViewState.LOADING
                }
                RecyclerStatus.ERROR -> {
                    binding.multiStateView.viewState = MultiStateView.ViewState.ERROR
                }
                RecyclerStatus.CONTENT->{
                    binding.multiStateView.viewState = MultiStateView.ViewState.CONTENT
                }
            }
        }

        recipesViewModel.error.observe(viewLifecycleOwner){errorMessage ->
            if(errorMessage != null){
                requireActivity().findViewById<TextView>(R.id.errorTV).text = errorMessage
            }
        }
        recipesViewModel.recipes.observe(viewLifecycleOwner) {
            recipesAdapter.updateRecipesList(it)
        }

        val searchButton : Button = root.findViewById(R.id.searchRecipesButton)
        searchButton.setOnClickListener {
            searchRecipe(recipesViewModel)
        }
        return root
    }

    fun searchRecipe(viewModel: RecipesViewModel) {
        val ingredients = "potatoes"
        databaseViewModel.addAPICallInfo(APICallInfo(ingredients, System.currentTimeMillis()))
        viewModel.loadRecipeResults(SPOONACULAR_APPID, ingredients)
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