package com.example.searchrecipe.apidataprovider

import com.example.searchrecipe.apiservice.FoodApi
import com.example.searchrecipe.models.RecipeSearchResult
import io.reactivex.Single
import javax.inject.Inject

class DataProviderImpl @Inject constructor(private val foodApi: FoodApi) : DataProviderInterface {

    override suspend fun getRecipeSearchResults(query: String): RecipeSearchResult {
        return foodApi.getRecipeSearchResults(query, FoodApi.PARAM_APP_ID_VALUE, FoodApi.PARAM_APP_KEY_VALUE)
    }
}