package com.example.searchrecipe.viewmodels

import com.example.searchrecipe.apidataprovider.DataProviderImpl
import com.example.searchrecipe.models.RecipeSearchResult
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner.Silent::class)
class SearchRecipeViewModelTest {

    @Mock
    lateinit var dataProviderImpl: DataProviderImpl
    private lateinit var viewModel: SearchRecipeViewModel
    private lateinit var jsonData: String
    private lateinit var mockResponseData: RecipeSearchResult
    private lateinit var singleResponseData: RecipeSearchResult
    private lateinit var responseData: RecipeSearchResult

    @Before
    fun setUp() {
        viewModel = SearchRecipeViewModel(dataProviderImpl)
        jsonData = Scanner(javaClass.getResourceAsStream(SEARCH_RESULTS_JSON)!!).useDelimiter("\\Z")
            .next()!!
        mockResponseData = Gson().fromJson(jsonData, RecipeSearchResult::class.java)!!
        singleResponseData = mockResponseData
    }

    @Test
    fun getSearchResultsData() =
        runBlockingTest {
            `when`(dataProviderImpl.getRecipeSearchResults(QUERY)).thenReturn(singleResponseData)
            responseData = viewModel.getSearchQueryResults(QUERY)

            assertEquals(responseData.hits.size, mockResponseData.hits.size)
            assertEquals(responseData.hits[1].bought, mockResponseData.hits[1].bought)
            assertEquals(responseData.hits[1].bookmarked, mockResponseData.hits[1].bookmarked)

        }

    companion object {
        const val QUERY = "Biryani"

        const val SEARCH_RESULTS_JSON = "/json/mockRecipeSearchResults.json"
    }
}