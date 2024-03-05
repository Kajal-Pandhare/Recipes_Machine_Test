package com.android.machine5.model

import com.google.gson.annotations.SerializedName

class RecipeResponse (
    @SerializedName("recipes")
    val recipes : ArrayList<Recipe>
)