package com.example.searchrecipe.di.modules

import com.example.searchrecipe.apidataprovider.DataProviderImpl
import com.example.searchrecipe.viewmodels.SearchRecipeViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun getRecipeSearchViewModel(dataProviderImpl: DataProviderImpl): SearchRecipeViewModel {
        return SearchRecipeViewModel(dataProviderImpl)
    }
}