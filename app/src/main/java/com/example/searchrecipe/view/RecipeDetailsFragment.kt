package com.example.searchrecipe.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.searchrecipe.R
import com.example.searchrecipe.models.Recipe
import kotlinx.android.synthetic.main.fragment_recipe_details.*


/**
 * Fragment to show the recipe details
 * Use the [RecipeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailsFragment : Fragment() {
    private var recipeDetail: Recipe? = null
    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as LauncherActivity).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeDetail = it.getParcelable(ARG_RECIPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()
        updateRecipeDetails()
    }

    private fun prepareRecyclerView() {
        ingredientAdapter = IngredientAdapter()

        recipeIngredientList.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = ingredientAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun updateRecipeDetails() {
        recipeDetail?.let {
            Glide.with(this)
                .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_find_recipes))
                .load(it.image)
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(image)
            image.contentDescription = it.label

            name.text = it.label
            ingredientAdapter.setRecipeList(it.ingredientLines)
        }
    }

    companion object {
        private const val ARG_RECIPE = "recipe"

        @JvmStatic
        fun newInstance(recipe: Recipe) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RECIPE, recipe)
                }
            }
    }
}