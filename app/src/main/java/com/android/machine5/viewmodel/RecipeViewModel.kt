package com.android.machine5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.machine5.model.Recipe
import com.android.machine5.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeViewModel(private val recipeRepository: RecipeRepository
) : ViewModel() {
    val recipeMutableLiveData = MutableLiveData<Boolean>()
    val recipes = ArrayList<Recipe>()

    fun fetchRecipes() {
        CoroutineScope(Dispatchers.IO).launch {
            val recipes = recipeRepository.fetchRecipes()

            withContext(Dispatchers.Main) {
                this@RecipeViewModel.recipes.addAll(recipes)
                recipeMutableLiveData.postValue(true)
            }
        }
    }
}