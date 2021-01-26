package com.example.searchrecipe.di.component

import com.example.searchrecipe.view.LauncherActivity
import com.example.searchrecipe.view.SearchRecipeFragment
import com.example.searchrecipe.di.modules.NetworkModule
import com.example.searchrecipe.di.modules.ViewModelModule
import com.example.searchrecipe.view.RecipeDetailsFragment
import dagger.Component

@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(launcherActivity: LauncherActivity)
    fun inject(searchRecipeFragment: SearchRecipeFragment)
    fun inject(recipeDetailsFragment: RecipeDetailsFragment)

}