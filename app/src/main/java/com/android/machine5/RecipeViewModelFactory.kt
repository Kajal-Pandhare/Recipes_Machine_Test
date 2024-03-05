package com.android.machine5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.machine5.repository.RecipeRepository
import com.android.machine5.viewmodel.RecipeViewModel

class RecipeViewModelFactory(private val recipeRepository: RecipeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeViewModel(recipeRepository) as T
    }
}