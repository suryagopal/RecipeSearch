package com.example.searchrecipe.view

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.searchrecipe.R
import com.example.searchrecipe.models.Hit
import com.example.searchrecipe.models.Recipe

/**
 * Adapter to Display Recipe SearchResults in the RecyclerView
 *  in the form of List
 */

class SearchRecipeResultsAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<SearchRecipeResultsAdapter.SearchResultsViewHolder>() {

    private var recipeResults = listOf<Hit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_view, parent, false)
        return SearchResultsViewHolder(view)
    }

    override fun getItemCount() = recipeResults.size

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {

        val data = recipeResults[position]
        holder.bindData(data)
        holder.itemView.setOnClickListener {
            clickListener.onClick(data.recipe)
        }
    }

    fun setData(recipeList: List<Hit>) {
        this.recipeResults = recipeList
    }


    class SearchResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val recipeImage = itemView.findViewById<AppCompatImageView>(R.id.recipeImage)
        private val recipeName = itemView.findViewById<AppCompatTextView>(R.id.recipeName)
        private val recipeEnergy = itemView.findViewById<AppCompatTextView>(R.id.recipeEnergy)

        @SuppressLint("SetTextI18n")
        fun bindData(data: Hit) {
            recipeName.text = data.recipe.label
            recipeEnergy.text = data.recipe.totalNutrients.ENERC_KCAL.quantity.toString()+ " " + data.recipe.totalNutrients.ENERC_KCAL.unit


            itemView.context?.let {
                Glide.with(it)
                    .load(data.recipe.image)
                    .apply(
                        RequestOptions().override(
                            recipeImage.drawable.intrinsicWidth,
                            recipeImage.drawable.intrinsicHeight
                        ).placeholder(R.mipmap.ic_launcher_round)
                    )
                    .into(recipeImage)
            }
        }
    }

    interface ClickListener {
        fun onClick(recipe: Recipe) {
        }
    }
}

