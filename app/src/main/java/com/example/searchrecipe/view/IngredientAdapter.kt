package com.example.searchrecipe.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchrecipe.R
import kotlinx.android.synthetic.main.ingredient_item.view.*
import java.util.*

class IngredientAdapter() : RecyclerView.Adapter<ViewHolder>() {

    private var mItems: List<String> = ArrayList()

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ingredientText.text = mItems[position]
    }

    fun setRecipeList(items: List<String>) {
        mItems = items
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ingredientText = view.ingredient_item_text
}