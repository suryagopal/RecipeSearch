package com.example.searchrecipe.apidataprovider

import com.example.searchrecipe.models.RecipeSearchResult
import io.reactivex.Single

interface DataProviderInterface {
    suspend fun getRecipeSearchResults(query: String): RecipeSearchResult
}