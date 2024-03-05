package com.android.machine5.repository

import com.android.machine5.model.Recipe
import com.android.machine5.network.RecipeApiService

class RecipeRepository(private val recipeApiService: RecipeApiService) {
    suspend fun fetchRecipes() :ArrayList<Recipe>{
        return recipeApiService.fetchRecipe().recipes
    }
}