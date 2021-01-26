package com.example.searchrecipe.apiservice

import com.example.searchrecipe.models.RecipeSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {

    @GET("search")
    suspend fun getRecipeSearchResults(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_APP_ID) appId: String,
        @Query(PARAM_APP_KEY) appKey: String
    ): RecipeSearchResult

    companion object {
        const val BASE_URL = "https://api.edamam.com/"
        const val PARAM_QUERY = "q"
        const val PARAM_APP_ID = "app_id"
        const val PARAM_APP_KEY = "app_key"
        const val PARAM_APP_ID_VALUE = "62b14e23"
        const val PARAM_APP_KEY_VALUE = "5ba8eeb7e0aa7d6d77de2f7823f5e50f"
    }
}