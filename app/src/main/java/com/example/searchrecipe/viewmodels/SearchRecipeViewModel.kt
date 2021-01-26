package com.example.searchrecipe.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchrecipe.apidataprovider.DataProviderImpl
import io.reactivex.Single
import com.example.searchrecipe.models.Hit
import com.example.searchrecipe.models.RecipeSearchResult
import javax.inject.Inject

class SearchRecipeViewModel @Inject constructor(private val dataProviderImpl: DataProviderImpl) :
    ViewModel() {

    val searchResultsMutableLiveData: MutableLiveData<RecipeSearchResult> = MutableLiveData()
    val errorResponseLiveData: MutableLiveData<String> = MutableLiveData()

    suspend fun getSearchQueryResults(query: String): RecipeSearchResult {
        return dataProviderImpl.getRecipeSearchResults(query)
    }

    fun getRecipesData(searchResults: RecipeSearchResult): List<Hit> {
        return searchResults.hits
    }
}