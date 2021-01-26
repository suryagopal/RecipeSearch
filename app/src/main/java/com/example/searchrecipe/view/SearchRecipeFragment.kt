package com.example.searchrecipe.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchrecipe.R
import com.example.searchrecipe.apidataprovider.DataProviderImpl
import com.example.searchrecipe.models.Recipe
import com.example.searchrecipe.models.RecipeSearchResult
import com.example.searchrecipe.viewmodels.SearchRecipeViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_search_recipe.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Fragment for Searching of Recipes and showing up the search results
 */
class SearchRecipeFragment : Fragment(), SearchRecipeResultsAdapter.ClickListener {

    @Inject
    lateinit var dataProviderImpl: DataProviderImpl

    @Inject
    lateinit var searchRecipeViewModel: SearchRecipeViewModel
    lateinit var searchRecipeResultsAdapter: SearchRecipeResultsAdapter
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var recipeSearchResult: RecipeSearchResult
    lateinit var recipeDetailsFragment: RecipeDetailsFragment

    override fun onAttach(context: Context) {
        (requireActivity() as LauncherActivity).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        compositeDisposable = CompositeDisposable()
        setupAdapter()

        searchRecipeViewModel.searchResultsMutableLiveData.observe(
            viewLifecycleOwner,
            Observer { searchResults ->

                searchResults?.let {
                    searchRecipeResultsAdapter.setData(searchRecipeViewModel.getRecipesData(it))
                    searchRecipeResultsAdapter.notifyDataSetChanged()
                }

            })


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()

                query?.let {
                    (requireActivity() as LauncherActivity).showProgress()

                    lifecycleScope.launchWhenStarted {
                        try {
                            recipeSearchResult =
                                searchRecipeViewModel.getSearchQueryResults(it.trim())
                        } catch (exception: Exception) {
                            Log.e(TAG, it)
                            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG)
                                .show()
                            searchRecipeViewModel.errorResponseLiveData.value = exception.message
                            searchRecipeViewModel.searchResultsMutableLiveData.value = null
                        }

                        withContext(Dispatchers.Main) {
                            (requireActivity() as LauncherActivity).hideProgress()
                            searchRecipeViewModel.searchResultsMutableLiveData.value =
                                recipeSearchResult
                            if (recipeSearchResult.count == 0) {
                                resultsRecyclerView.visibility = View.GONE
                                resultError.visibility = View.VISIBLE
                            } else {
                                resultsRecyclerView.visibility = View.VISIBLE
                                resultError.visibility = View.GONE
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupAdapter() {
        searchRecipeResultsAdapter = SearchRecipeResultsAdapter(this@SearchRecipeFragment)

        resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchRecipeResultsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onClick(recipe: Recipe) {
        recipeDetailsFragment = RecipeDetailsFragment.newInstance(recipe)

        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            replace(R.id.container, recipeDetailsFragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        const val TAG = "SearchRecipeFragment"
        fun newInstance() = SearchRecipeFragment()
    }
}
