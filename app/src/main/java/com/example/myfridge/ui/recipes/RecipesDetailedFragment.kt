package com.example.myfridge.ui.recipes

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myfridge.R
import com.example.myfridge.data.database.APICallInfo
import com.example.myfridge.data.recipes.RecipeItem
import com.example.myfridge.databinding.FragmentRecipesDetailedBinding
import com.example.myfridge.ui.database.DatabaseViewModel
import com.skydoves.transformationlayout.TransformationLayout
import com.skydoves.transformationlayout.onTransformationEndContainer

class RecipesDetailedFragment: Fragment() {
    private val databaseViewModel: DatabaseViewModel.APICallInfoViewModel by viewModels()
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
        val recipesDetailedViewModel =
            ViewModelProvider(this)[RecipesDetailedViewModel::class.java]
        _binding = FragmentRecipesDetailedBinding.inflate(inflater, container, false)
        val root: View = binding.root


        ViewCompat.setTransitionName(binding.detailedRecipeMainText,"image${args.position}")
        val recipe = args.recipe

        searchRecipeDetailed(recipesDetailedViewModel, recipe)

        recipesDetailedViewModel.recipes.observe(viewLifecycleOwner) { recipes ->
            if (recipes != null) {
                Glide.with(binding.detailedRecipeImage).load(recipes.image).into(binding.detailedRecipeImage)
                binding.detailedRecipeMainText.apply {
                    text = "${recipes.title} Recipe"
                }
                binding.detailedRecipeServingsImage.apply {
                    setImageResource(R.drawable.ic_detailed_recipe_servings)
                }
                binding.detailedRecipeServingsText.apply {
                    text = "Serves: ${recipes.servings}"
                }
                binding.detailedRecipeReadyInMinutesImage.apply {
                    setImageResource(R.drawable.ic_detailed_recipe_readyinminutes)
                }
                binding.detailedRecipeReadyInMinutesText.apply {
                    text = "${recipes.readyInMinutes} minutes"
                }
                binding.detailedRecipeLikesImage.apply {
                    setImageResource(R.drawable.ic_detailed_recipe_likes)
                }
                binding.detailedRecipeLikesText.apply {
                    text = "${recipes.aggregateLikes}"
                }
                binding.detailedRecipeSummaryText.apply {
                    text = Html.fromHtml(recipes.summary, HtmlCompat.FROM_HTML_MODE_LEGACY)
                }
            }
        }
        binding.detailedRecipeMainText.transitionName = "possible${args.position}"
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        onTransformationEndContainer(requireArguments().getBundle("possible${args.position}") as TransformationLayout.Params)
    }

    fun searchRecipeDetailed(viewModel: RecipesDetailedViewModel, recipe: RecipeItem) {
        viewModel.loadDetailedRecipeResults(recipe!!.id, SPOONACULAR_APPID)
    }
}