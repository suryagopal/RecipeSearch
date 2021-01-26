package com.example.searchrecipe.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchrecipe.apidataprovider.DataProviderImpl
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Factory class for ViewModels
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(dataProviderImpl: DataProviderImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass === SearchRecipeViewModel::class -> modelClass as T
            else -> throw IllegalArgumentException("unable to find ViewModel class")
        }
    }
}