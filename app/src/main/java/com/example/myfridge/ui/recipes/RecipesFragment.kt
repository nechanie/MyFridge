package com.example.myfridge.ui.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfridge.BuildConfig
import com.example.myfridge.R
import com.example.myfridge.data.database.APICallInfo
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.FragmentRecipesBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import com.example.myfridge.ui.home.HomeAdapter
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.addTransformation
import com.skydoves.transformationlayout.onTransformationStartContainer

const val SPOONACULAR_APPID = BuildConfig.SPOONACULAR_API_KEY
class RecipesFragment : Fragment() {
    private val databaseViewModel: DatabaseViewModel.APICallInfoViewModel by viewModels()
    private val fridgedatabaseViewModel: DatabaseViewModel.FridgeItemInfoViewModel by viewModels()
    private var _binding: FragmentRecipesBinding? = null
    private var ingredients: String = "bananas"
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
        recipesViewModel.recipes.observe(viewLifecycleOwner) {
            recipesAdapter.updateRecipesList(it)
        }

        fridgedatabaseViewModel.fridgeItemNameAll.observe(viewLifecycleOwner) {
            ingredients = it!!.joinToString(separator=",+")
            Log.d("RecipesFragment", "${ingredients}")
        }

        val searchButton : Button = root.findViewById(R.id.searchRecipesButton)
        searchButton.setOnClickListener {
            searchRecipe(recipesViewModel, ingredients)
        }
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        onTransformationStartContainer()
//        val fragment = RecipesDetailedFragment()
//        val bundle = requireView().findViewById<TransformationLayout>(R.id.recipe_item_transformation_layout).getBundle("TransformationParams")
//        bundle.putParcelable(MainSingleDetailFragment.posterKey, poster)
//        fragment.arguments = bundle
    }
    fun searchRecipe(viewModel: RecipesViewModel, ingredients: String) {
        databaseViewModel.addAPICallInfo(APICallInfo(ingredients, System.currentTimeMillis()))
        viewModel.loadRecipeResults(SPOONACULAR_APPID, ingredients)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun forwardDetailedRecipe(recipe: RecipeItem, position: Int, currentView: View){
//        val text = currentView.findViewById<TextView>(R.id.recipe_item_name)
//        ViewCompat.setTransitionName(text,"possible$position")
//        Log.d("setTransName", text.transitionName)
//        val extra = FragmentNavigatorExtras(text to "possible$position")
        val directions = RecipesFragmentDirections.actionNavRecipesToRecipesDetailedFragment(recipe, position)
        findNavController().navigate(directions)
        return
    }
}